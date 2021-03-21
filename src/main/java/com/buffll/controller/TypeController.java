package com.buffll.controller;

import com.buffll.entity.Type;
import com.buffll.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * 分类功能的控制器
 * @author pxz
 * @create 2021-03-11 18:44
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
	@Autowired
	private TypeService typeService;
	
	/**
	 * 展示分页列表的页面
	 */
	@GetMapping("/types")
	public String toTypeListPage(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
		String orderBy = "id";
		PageHelper.startPage(pageNum, 5, orderBy);
		List<Type> list = typeService.getAllTypes();
		PageInfo<Type> pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		return "pages/admin/admin_types";
	}
	
	/**
	 * 跳转到添加分类页面
	 * @return
	 */
	@GetMapping("/types/input")
	public String toAddTypePage(Model model) {
		model.addAttribute("type", new Type());
		return "pages/admin/admin_type_input";
	}
	
	/**
	 * 跳转到编辑分类的页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/types/{id}/input")
	public String toEditTypePage(@PathVariable Long id, Model model) {
		model.addAttribute("type", typeService.getTypeById(id));
		return "pages/admin/admin_type_input";
	}
	
	/**
	 * 新增分类
	 */
	@PostMapping("/types")
	public String saveType(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
		if (typeService.getTypeByName(type.getName()) != null) {
			//已存在相同的类型名
			//添加自定义验证结果
			result.rejectValue("name", "nameError", "该分类已存在,不能重复添加");
		}
		if (result.hasErrors()) {
			return "pages/admin/admin_type_input";
		}
		if (typeService.saveType(type) == null) {
			//新增失败
			attributes.addFlashAttribute("message", "添加失败");
		} else {
			attributes.addFlashAttribute("message", "添加成功");
		}
		return "redirect:/admin/types";
	}
	
	/**
	 * 修改分类
	 * @param type
	 * @param result
	 * @param id
	 * @param attributes
	 * @return
	 */
	@PostMapping("/types/{id}")
	public String editType(@Valid Type type, BindingResult result,
	                       @PathVariable Long id, RedirectAttributes attributes) {
		if (typeService.getTypeByName(type.getName()) != null) {
			result.rejectValue("name", "nameError", "该分类已存在,不能重复添加");
		}
		if (result.hasErrors()) {
			return "pages/admin/admin_type_input";
		}
		
		if (typeService.updateType(type) == null) {
			attributes.addFlashAttribute("message", "更新失败");
		} else {
			attributes.addFlashAttribute("message", "更新成功");
		}
		return "redirect:/admin/types";
	}
	
	/**
	 * 删除分类
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/types/{id}/delete")
	public String deleteType(@PathVariable Long id, RedirectAttributes attributes) {
		typeService.deleteType(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/types";
	}
}
