package com.buffll.service.impl;

import com.buffll.dao.BlogDao;
import com.buffll.entity.Blog;
import com.buffll.exception.NotFoundException;
import com.buffll.service.BlogService;
import com.buffll.utils.MarkdownUtils;
import com.buffll.utils.MyUtils;
import com.buffll.vo.BlogQueryVo;
import com.buffll.vo.FirstPageBlogVo;
import com.buffll.vo.SearchBlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 博客功能的业务层实现类
 * @author pxz
 * @create 2021-03-09 16:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogServiceImpl implements BlogService {
	@Autowired(required = false)
	private BlogDao blogDao;
	
	@Override
	public Blog getBlogById(Long id) {
		return blogDao.getBlogById(id);
	}
	
	@Override
	public List<Blog> getIndexBlog() {
		return blogDao.getIndexBlog();
	}
	
	@Override
	public Blog getAndConvert(Long id) {
		Blog blog = blogDao.getBlogById(id);
		if (blog == null) {
			throw new NotFoundException("该博客不存在");
		}
		Blog b = new Blog();
		//将获取到的blog复制一份,对这个对象的副本进行格式转换,否则的话会改变数据库中的内容
		BeanUtils.copyProperties(blog, b);
		String content = b.getContent();
		b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
		blogDao.updateViews(id);
		return b;
	}
	
	@Override
	public List<Blog> getAllRecommendBlog() {
		return blogDao.getAllRecommendBlog();
	}
	
	@Override
	public List<BlogQueryVo> getAllBlogQuery() {
		return blogDao.getAllBlogQuery();
	}
	
	@Override
	public List<Blog> getBlogByTypeId(Long typeId) {
		return blogDao.getBlogByTypeId(typeId);
	}
	
	@Override
	public List<Blog> getBlogByTagId(Long tagId) {
		return blogDao.getBlogByTagId(tagId);
	}
	
	@Override
	public List<Blog> getFooterRecommendBlog() {
		return blogDao.getFooterRecommendBlog();
	}
	
	@Override
	public String getFirstPictureByBlogId(Long blogId) {
		return blogDao.getFirstPictureByBlogId(blogId);
	}
	
	@Override
	public Integer countBlog() {
		return blogDao.countBlog();
	}
	
	@Override
	public Map<String, List<Blog>> archiveBlog() {
		//获取所有博客的所有年份的集合
		List<String> years = blogDao.getYearForBlogs();
		Map<String, List<Blog>> map = new TreeMap<>(Comparator.reverseOrder());
		for (String year : years) {
			map.put(year, blogDao.getBlogByYear(year));
		}
		return map;
	}
	
	@Override
	public List<BlogQueryVo> searchAdminBlog(SearchBlogVo searchBlog) {
		return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
	}
	
	@Override
	public List<FirstPageBlogVo> searchBlog(String query) {
		return blogDao.searchBlog(query);
	}
	
	@Override
	public Integer saveBlog(Blog blog) {
		if (blog.getId() == null) {
			//id为空表示新增,否则是修改
			blog.setCreateTime(new Date());
			blog.setUpdateTime(new Date());
			blog.setViews(1);
			if ("".equals(blog.getFlag()) || blog.getFlag() == null) {
				//设置默认为原创
				blog.setFlag("原创");
			}
		}
		return blogDao.saveBlog(blog);
	}
	
	@Override
	public Integer updateBlog(Blog blog) {
		Blog b = blogDao.getBlogById(blog.getId());
		if (b != null) {
			BeanUtils.copyProperties(blog, b, MyUtils.getNullPropertyNames(blog));
			b.setUpdateTime(new Date());
			return blogDao.updateBlog(b);
		} else {
			throw new NotFoundException("该博客不存在");
		}
	}
	
	@Override
	public void deleteBlog(Long id) {
		blogDao.deleteBlog(id);
	}
}
