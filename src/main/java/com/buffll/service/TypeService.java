package com.buffll.service;

import com.buffll.entity.Type;

import java.util.List;

/**
 * 分类功能的业务层接口
 * @author pxz
 * @create 2021-02-25 16:05
 */
public interface TypeService {
	
	/**
	 * 根据id查询分类
	 * @param id
	 * @return
	 */
	Type getTypeById(Long id);
	
	/**
	 * 获取所有分类(非分页查询)
	 */
	List<Type> getAllTypes();
	
	/**
	 * 根据名称查询分类
	 * @param name
	 * @return
	 */
	Type getTypeByName(String name);
	
	/**
	 * 获取博客的分类
	 * @return
	 */
	List<Type> getBlogType();
	
	/**
	 * 新增分类
	 * @param type
	 * @return
	 */
	Integer saveType(Type type);
	
	/**
	 * 修改分类
	 * @param type 修改后的分类对象
	 * @return
	 */
	Integer updateType(Type type);
	
	/**
	 * 根据id删除分类
	 * @param id
	 */
	void deleteType(Long id);
}
