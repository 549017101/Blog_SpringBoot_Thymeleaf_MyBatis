package com.buffll.dao;

import com.buffll.entity.Type;

import java.util.List;

/**
 * 分类功能的持久层接口
 * @author pxz
 * @create 2021-02-25 16:19
 */
public interface TypeDao {
	
	/**
	 * 根据id获取分类
	 * @param id 分类的id
	 * @return
	 */
	Type getTypeById(Long id);
	
	/**
	 * 根据名称查找分类
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
	 * 获取所有分类,返回分类的集合
	 * @return
	 */
	List<Type> getAllTypes();
	
	/**
	 * 新增分类
	 * @param type
	 * @return
	 */
	Integer saveType(Type type);
	
	/**
	 * 修改分类<br>
	 * 先根据id查询,再修改
	 * @param type
	 * @return
	 */
	Integer updateType(Type type);
	
	/**
	 * 删除分类
	 * @param id 分类的id
	 */
	void deleteType(Long id);
}
