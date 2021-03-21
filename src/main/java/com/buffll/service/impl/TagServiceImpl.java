package com.buffll.service.impl;

import com.buffll.dao.TagDao;
import com.buffll.entity.Tag;
import com.buffll.service.TagService;
import com.buffll.utils.MyUtils;
import com.buffll.vo.BlogAndTagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签功能的业务层实现类
 * @author pxz
 * @create 2021-03-09 16:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TagServiceImpl implements TagService {
	@Autowired(required = false)
	private TagDao tagDao;
	
	@Override
	public Tag getTagById(Long id) {
		return tagDao.getTagById(id);
	}
	
	@Override
	public Tag getTagByName(String name) {
		return tagDao.getTagByName(name);
	}
	
	@Override
	public List<Tag> getAllTags() {
		return tagDao.getAllTags();
	}
	
	@Override
	public List<Tag> getAllTagsByIds(String ids) {
		return tagDao.getAllTagsByIds(MyUtils.convertToList(ids));
	}
	
	@Override
	public List<BlogAndTagVo> getTagsByBlogId(Long blogId) {
		return tagDao.getTagsByBlogId(blogId);
	}
	
	@Override
	public List<Tag> getBlogTag() {
		return tagDao.getBlogTag();
	}
	
	@Override
	@Transactional
	public Integer saveTag(Tag tag) {
		return tagDao.saveTag(tag);
	}
	
	@Override
	@Transactional
	public Integer saveTagAboutBlog(Long blogId, Long tagId) {
		return tagDao.saveTagAboutBlog(blogId, tagId);
	}
	
	@Override
	@Transactional
	public Integer updateTag(Tag tag) {
		return tagDao.updateTag(tag);
	}
	
	@Override
	@Transactional
	public void deleteTag(Long id) {
		tagDao.deleteTag(id);
	}
	
	@Override
	@Transactional
	public void deleteTagAboutBlog(Long blogId) {
		tagDao.deleteTagAboutBlog(blogId);
	}
}
