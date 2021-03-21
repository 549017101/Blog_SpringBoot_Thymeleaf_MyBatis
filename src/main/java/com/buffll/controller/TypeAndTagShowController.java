package com.buffll.controller;

import com.buffll.entity.Blog;
import com.buffll.entity.Tag;
import com.buffll.entity.Type;
import com.buffll.service.BlogService;
import com.buffll.service.TagService;
import com.buffll.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 分类和标签展示页的控制器
 * @author pxz
 * @create 2021-03-12 13:41
 */
@Controller
public class TypeAndTagShowController {
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TagService tagService;
	
	/**
	 * 根据不同的分类展示相应的博客
	 */
	@GetMapping("/types/{id}")
	public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
		PageHelper.startPage(pageNum, 100);
		
		List<Type> types = typeService.getBlogType();
		
		if(id == 0){
		    //id为0表示从首页进入,默认显示第一个分类
			id = types.get(0).getId();
		}
		
		List<Blog> blogs = blogService.getBlogByTypeId(id);
		
		PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
		
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("types", types);
		//activeTypeId 代表当前处于活跃状态(被选中)的分类,用于在前端页面进行高亮展示
		model.addAttribute("activeTypeId", id);
		return "pages/types";
	}
	
	/**
	 * 根据不同的标签展示相应的博客
	 */
	@GetMapping("/tags/{id}")
	public String tags(Model model, @PathVariable Long id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
		PageHelper.startPage(pageNum, 1000);
		
		List<Tag> tags = tagService.getBlogTag();
		if (id == 0) {
			id = tags.get(0).getId();
		}
		
		List<Blog> blogs = blogService.getBlogByTagId(id);
		PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
		
		model.addAttribute("tags", tags);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("activeTagId", id);
		return "pages/tags";
	}
}
