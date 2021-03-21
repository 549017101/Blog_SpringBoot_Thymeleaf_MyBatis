package com.buffll.service;

import com.buffll.entity.Tag;
import com.buffll.vo.BlogAndTagVo;

import java.util.List;

/**
 * 标签功能的业务层接口
 * @author pxz
 * @create 2021-02-25 23:55
 */
public interface TagService {
	
	/**
	 * 根据id查询标签
	 * @param id
	 * @return
	 */
	Tag getTagById(Long id);
	
	/**
	 * 通过多个id值的集合获取标签
	 * @param ids
	 * @return
	 */
	List<Tag> getAllTagsByIds(String ids);
	
	/**
	 * 获取所有标签(非分页)
	 * @return
	 */
	List<Tag> getAllTags();
	
	/**
	 * 通过博客id查询对应的标签集合
	 * @param blogId
	 * @return
	 */
	List<BlogAndTagVo> getTagsByBlogId(Long blogId);
	
	/**
	 * 获取博客的标签
	 * @return
	 */
	List<Tag> getBlogTag();
	
	/**
	 * 根据名称查询标签
	 * @param name
	 * @return
	 */
	Tag getTagByName(String name);
	
	/**
	 * 保存标签
	 * @param type
	 * @return
	 */
	Integer saveTag(Tag type);
	
	/**
	 * 对博客新增(或修改)标签<br>
	 * 操作博客和标签的中间表
	 * @return
	 */
	Integer saveTagAboutBlog(Long blogId, Long tagId);
	
	/**
	 * 修改标签
	 * @param type
	 * @return
	 */
	Integer updateTag(Tag type);
	
	/**
	 * 根据id删除分类
	 * @param id
	 */
	void deleteTag(Long id);
	
	/**
	 * 根据博客id删除所关联的标签<br>
	 * 操作博客和标签的中间表,新增或修改博客时,先调用此方法将博客所对应的标签删除,再插入表单中所选择的标签
	 * @param blogId 当前博客的id
	 */
	void deleteTagAboutBlog(Long blogId);
}
