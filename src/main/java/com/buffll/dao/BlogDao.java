package com.buffll.dao;

import com.buffll.entity.Blog;
import com.buffll.vo.BlogQueryVo;
import com.buffll.vo.FirstPageBlogVo;
import com.buffll.vo.SearchBlogVo;

import java.util.List;

/**
 * 博客的dao接口
 * @author pxz
 * @create 2021-03-09 16:49
 */
public interface BlogDao {
	
	/**
	 * 根据博客id查询博客
	 * @param id
	 * @return
	 */
	Blog getBlogById(Long id);
	
	/**
	 * 获取项目首页展示的博客
	 * @return
	 */
	List<Blog> getIndexBlog();
	
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
	 * 获取项目首页显示的博客列表
	 * @return
	 */
	List<FirstPageBlogVo> getFirstPageBlog();
	
	/**
	 * 获取项目首页展示的推荐博客列表
	 * @return
	 */
	List<FirstPageBlogVo> getFirstPageRecommendBlog();
	
	/**
	 * 获取底部推荐的博客(取最新的三条数据)
	 * @return 底部推荐的博客对象
	 */
	List<Blog> getFooterRecommendBlog();
	
	/**
	 * 根据指定年份查询博客
	 * @param year 博客所属的年份
	 * @return
	 */
	List<Blog> getBlogByYear(String year);
	
	/**
	 * 查询所有博客的年份,返回一个字符串类型的List
	 * @return
	 */
	List<String> getYearForBlogs();
	
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
	 * 搜索博客(后台管理搜索)
	 * @param searchBlog 封装搜索的博客数据
	 * @return
	 */
	List<BlogQueryVo> searchByTitleOrTypeOrRecommend(SearchBlogVo searchBlog);
	
	/**
	 * 搜索博客(全局搜索)
	 * @param query 搜索的关键字
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
	 * 修改博客
	 * @param blog 需要修改的博客对象
	 * @return
	 */
	Integer updateBlog(Blog blog);
	
	/**
	 * 更新浏览量
	 * @param id
	 * @return 更新后的浏览量
	 */
	Integer updateViews(Long id);
	
	/**
	 * 删除博客
	 * @param id
	 */
	void deleteBlog(Long id);
}
