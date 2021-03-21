package com.buffll.controller;

import com.buffll.entity.Blog;
import com.buffll.entity.Tag;
import com.buffll.entity.Type;
import com.buffll.service.BlogService;
import com.buffll.service.TagService;
import com.buffll.service.TypeService;
import com.buffll.service.UserService;
import com.buffll.vo.FirstPageBlogVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author pxz
 * @create 2021-03-11 21:49
 */
@Controller
public class IndexController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到项目首页
	 * @return
	 */
	@GetMapping("/")
	public String toIndex(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
		PageHelper.startPage(pageNum, 5);
		
		List<Blog> allBlog = blogService.getIndexBlog();
		List<Type> allType = typeService.getBlogType();
		List<Tag> allTag = tagService.getBlogTag();
		List<Blog> recommendBlog = blogService.getAllRecommendBlog();
		
		PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("tags", allTag);
		model.addAttribute("types", allType);
		model.addAttribute("recommendBlogs", recommendBlog);
		return "index";
	}
	
	/**
	 * 跳转到博客详情页面,并根据博客id查询相对应的博客
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/blog/{id}")
	public String toBlogDetailPage(@PathVariable Long id, Model model) {
		Long userId = blogService.getBlogById(id).getUserId();
		model.addAttribute("blog", blogService.getAndConvert(id));
		model.addAttribute("tags",tagService.getTagsByBlogId(id));
		model.addAttribute("user",userService.findAvatarAndNickname(userId));
		return "pages/blog";
	}
	
	/**
	 * 跳转到分类页面
	 * @return
	 */
	@GetMapping("/types")
	public String toTypes() {
		return "redirect:/types/0";
	}
	
	/**
	 * 跳转到标签页面
	 * @return
	 */
	@GetMapping("/tags")
	public String toTags() {
		return "redirect:/tags/0";
	}
	
	/**
	 * 跳转到关于我页面
	 * @return
	 */
	@GetMapping("/about")
	public String about() {
		return "pages/about";
	}
	
	/**
	 * 底部的推荐博客(取最新的三条数据)
	 * @return
	 */
	@GetMapping("/footer/newblog")
	public String footerNewBlogs(Model model) {
		model.addAttribute("newBlogs",blogService.getFooterRecommendBlog());
		return "commons/fragments :: newblogList";
	}
	
	/**
	 * 全局搜索搜索功能
	 */
	@PostMapping("/search")
	public String search(Model model,
	                     @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
	                     @RequestParam String query) {
		PageHelper.startPage(pageNum, 5);
		List<FirstPageBlogVo> searchBlog = blogService.searchBlog(query);
		PageInfo<FirstPageBlogVo> pageInfo = new PageInfo<>(searchBlog);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("query", query);
		return "pages/search";
	}
	
	/**
	 * 全局搜索后的翻页功能
	 */
	@GetMapping("/search")
	public String searchPage(Model model,
	                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
	                         @RequestParam String query) {
		PageHelper.startPage(pageNum, 5);
		List<FirstPageBlogVo> searchBlog = blogService.searchBlog(query);
		PageInfo<FirstPageBlogVo> pageInfo = new PageInfo<>(searchBlog);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("query", query);
		return "pages/search";
	}
}
