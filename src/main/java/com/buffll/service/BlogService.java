package com.buffll.service;

import com.buffll.entity.Blog;
import com.buffll.vo.BlogQueryVo;
import com.buffll.vo.FirstPageBlogVo;
import com.buffll.vo.SearchBlogVo;

import java.util.List;
import java.util.Map;

/**
 * 博客功能的业务层接口
 * @author pxz
 * @create 2021-02-26 10:29
 */
public interface BlogService {
	/**
	 * 根据id查询博客
	 * @param id
	 * @return
	 */
	Blog getBlogById(Long id);
	
	/**
	 * 获取首页博客
	 * @return
	 */
	List<Blog> getIndexBlog();
	
	/**
	 * 获取并转换博客(将markdown语法转换为html),不改变数据库中的博客内容
	 * @param id
	 * @return
	 */
	Blog getAndConvert(Long id);
	
	/**
	 * 获取首页展示的推荐博客
	 * @return
	 */
	List<Blog> getAllRecommendBlog();
	
	/**
	 * 查询文章管理列表的博客数据
	 * @return
	 */
	List<BlogQueryVo> getAllBlogQuery();
	
	/**
	 * 根据分类id获取博客
	 * @param typeId
	 * @return
	 */
	List<Blog> getBlogByTypeId(Long typeId);
	
	/**
	 * 根据标签id获取博客
	 * @param tagId
	 * @return
	 */
	List<Blog> getBlogByTagId(Long tagId);
	
	/**
	 * 获取底部推荐的博客(取最新的三条数据)
	 * @return
	 */
	List<Blog> getFooterRecommendBlog();
	
	/**
	 * 根据博客id获取博客的首图地址
	 * @param blogId 博客id
	 * @return
	 */
	String getFirstPictureByBlogId(Long blogId);
	
	/**
	 * 获取博客总条数
	 * @return
	 */
	Integer countBlog();
	
	/**
	 * 将博客按照条件归档,存放在Map中
	 * @return
	 */
	Map<String, List<Blog>> archiveBlog();
	
	/**
	 * 搜索博客(后台管理搜索)
	 * @return
	 */
	List<BlogQueryVo> searchAdminBlog(SearchBlogVo searchBlog);
	
	/**
	 * 搜索博客(全局搜索)
	 * @param query
	 * @return
	 */
	List<FirstPageBlogVo> searchBlog(String query);
	
	/**
	 * 新增博客
	 * @param blog
	 * @return
	 */
	Integer saveBlog(Blog blog);
	
	/**
	 * 修改博客 (先根据id查询博客,在赋给它新的博客对象)
	 * @return
	 */
	Integer updateBlog(Blog blog);
	
	/**
	 * 删除博客
	 * @param id
	 */
	void deleteBlog(Long id);
}
