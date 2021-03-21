package com.buffll.controller;

import com.buffll.entity.Blog;
import com.buffll.entity.Tag;
import com.buffll.entity.User;
import com.buffll.service.BlogService;
import com.buffll.service.TagService;
import com.buffll.service.TypeService;
import com.buffll.utils.MyUtils;
import com.buffll.vo.BlogAndTagVo;
import com.buffll.vo.BlogQueryVo;
import com.buffll.vo.SearchBlogVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 博客功能的控制器
 * @author pxz
 * @create 2021-03-11 20:47
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
	@Value("${firstPicture.dir}")
	private String uploadPath;
	
	@Value("${imgPath}")
	private String imgPath;
	
	private static final String INPUT = "pages/admin/admin_blogs_input";
	private static final String LIST = "pages/admin/admin_blogs";
	private static final String REDIRECT_LIST = "redirect:/admin/blogs";
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TagService tagService;
	
	/**
	 * 初始化分类和标签
	 * @param model
	 */
	private void initTypeAndTag(Model model) {
		model.addAttribute("types", typeService.getAllTypes());
		model.addAttribute("tags", tagService.getAllTags());
	}
	
	/**
	 * 跳转到博客管理页面
	 * @return
	 */
	@GetMapping("/blogs")
	public String toBlogListPage(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
		//按照更新时间倒序排列
		String orderBy = "update_time desc";
		PageHelper.startPage(pageNum, 5, orderBy);
		List<BlogQueryVo> list = blogService.getAllBlogQuery();
		PageInfo<BlogQueryVo> pageInfo = new PageInfo<>(list);
		model.addAttribute("types", typeService.getAllTypes());
		model.addAttribute("pageInfo", pageInfo);
		return LIST;
	}
	
	/**
	 * 跳转到新增博客页面
	 * @return
	 */
	@GetMapping("/blogs/input")
	public String toAddBlogPage(Model model) {
		initTypeAndTag(model);
		model.addAttribute("blog", new Blog());
		return INPUT;
	}
	
	/**
	 * 跳转到修改博客页面
	 * @return
	 */
	@GetMapping("/blogs/{id}/input")
	public String toEditBlogPage(@PathVariable Long id, Model model) {
		initTypeAndTag(model);
		Blog blog = blogService.getBlogById(id);
		List<BlogAndTagVo> tagsByBlogId = tagService.getTagsByBlogId(id);
		
		//设置当前博客所包含的标签
		StringBuffer ids = new StringBuffer();
		boolean flag = false;
		for (BlogAndTagVo tag : tagsByBlogId) {
			if (flag) {
				ids.append(",");
			} else {
				flag = true;
			}
			ids.append(tag.getId());
		}
		blog.setTagIds(ids.toString());
		
		model.addAttribute("blog", blog);
		model.addAttribute("currentBlogTypeId", blog.getTypeId());
		return INPUT;
	}
	
	/**
	 * 博客新增修改二合一方法
	 * @param blog
	 * @param attributes
	 * @param session
	 * @return
	 */
	@PostMapping("/blogs")
	public String blogEditOrSave(Blog blog, RedirectAttributes attributes, HttpSession session, @RequestParam ("tagIds")String tagIds, @RequestParam(value = "file") MultipartFile firstPicture) {
		blog.setUser((User) session.getAttribute("user"));
		blog.setType(typeService.getTypeById(blog.getType().getId()));
		blog.setTypeId(blog.getType().getId());
		blog.setUserId(blog.getUser().getId());
		
		Long blogId = blog.getId();
		Integer i;
		if (blogId == null) {
			//新增博客
			
			MyUtils.uploadFirstPicture(blog, firstPicture, uploadPath, Boolean.FALSE);
			i = blogService.saveBlog(blog);
			for (Tag t : tagService.getAllTagsByIds(tagIds)) {
				//这里的blogId已经在mapper文件中处理过,insert之后就可以获取到自增的主键
				tagService.saveTagAboutBlog(blog.getId(), t.getId());
			}
		} else {
			//修改博客
			
			MyUtils.uploadFirstPicture(blog,firstPicture, uploadPath,Boolean.TRUE);
			tagService.deleteTagAboutBlog(blogId);
			for(Tag t : tagService.getAllTagsByIds(tagIds)){
				tagService.saveTagAboutBlog(blogId,t.getId());
			}
			i = blogService.updateBlog(blog);
		}
		
		if (i == null) {
			attributes.addFlashAttribute("message", "操作失败");
		} else {
			attributes.addFlashAttribute("message", "操作成功");
		}
		return REDIRECT_LIST;
	}
	
	/**
	 * 删除博客
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/blogs/{id}/delete")
	public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes) {
		String firstPicture = blogService.getFirstPictureByBlogId(id);
//		String filePath = imgPath + "\\" + firstPicture;  //windows系统
		String filePath = imgPath + firstPicture;    //linux系统
		
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			//删除博客的首图文件
			file.delete();
		}

		//删除博客之前先删除他所关联的标签
		tagService.deleteTagAboutBlog(id);
		blogService.deleteBlog(id);
		attributes.addFlashAttribute("message", "删除成功");
		return REDIRECT_LIST;
	}
	
	/**
	 * 搜索博客(后台管理搜索)
	 * @param searchBlog
	 * @param model
	 * @param pageNum
	 * @return
	 */
	@PostMapping("/blogs/search")
	public String adminSearchBlog(SearchBlogVo searchBlog, Model model,
	                     @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
		//一定要先写分页,否则分页无效
		PageHelper.startPage(pageNum, 5);
		List<BlogQueryVo> blogBySearch = blogService.searchAdminBlog(searchBlog);
		PageInfo<BlogQueryVo> pageInfo = new PageInfo<>(blogBySearch);
		model.addAttribute("pageInfo", pageInfo);
		return "pages/admin/admin_blogs :: blogList";
	}
}
