package com.buffll.dao;

import com.buffll.entity.Tag;
import com.buffll.vo.BlogAndTagVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签功能的持久层接口
 * @author pxz
 * @create 2021-02-25 23:52
 */
public interface TagDao {
	
	/**
	 * 根据id获取标签
	 * @param id 标签的id
	 * @return
	 */
	Tag getTagById(Long id);
	
	/**
	 * 通过多个id值的集合获取标签
	 * @param tagIds
	 * @return
	 */
	List<Tag> getAllTagsByIds(@Param("tagIds")List<Long> tagIds);
	
	/**
	 * 根据名称查找标签
	 * @param name
	 * @return
	 */
	Tag getTagByName(String name);
	
	/**
	 * 获取所有标签,返回标签的集合
	 * @return
	 */
	List<Tag> getAllTags();
	
	/**
	 * 获取博客的标签
	 * @return
	 */
	List<Tag> getBlogTag();
	
	/**
	 * 通过博客id查询对应的标签集合
	 * @param blogId
	 * @return
	 */
	List<BlogAndTagVo> getTagsByBlogId(Long blogId);
	
	/**
	 * 新增标签
	 * @param tag
	 * @return
	 */
	Integer saveTag(Tag tag);
	
	/**
	 * 对博客新增(或修改)标签<br>
	 * 操作博客和标签的中间表
	 * @return
	 */
	Integer saveTagAboutBlog(Long blogsId, Long tagsId);
	
	/**
	 * 修改标签
	 * @param tag
	 * @return
	 */
	Integer updateTag(Tag tag);
	
	/**
	 * 删除标签
	 * @param id 标签的id
	 */
	void deleteTag(Long id);
	
	/**
	 * 根据博客id删除所关联的标签<br>
	 * 操作博客和标签的中间表,新增或修改博客时,先调用此方法将博客所对应的标签删除,再插入表单中所选择的标签
	 * @param blogsId 当前博客的id
	 */
	void deleteTagAboutBlog(Long blogsId);
}
