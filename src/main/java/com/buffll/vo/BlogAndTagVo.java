package com.buffll.vo;

import java.io.Serializable;

/**
 * 博客和标签对应关系的值对象
 * @author pxz
 * @create 2021-03-13 20:30
 */
public class BlogAndTagVo implements Serializable {
	/**
	 * 标签id
	 */
	private Long id;
	
	/**
	 * 标签名称
	 */
	private String name;
	
	/**
	 * 博客id(中间表的属性)
	 */
	private Long blogsId;
	
	/**
	 * 博客所属的标签id(中间表的属性)
	 */
	private Long tagsId;
	
	public BlogAndTagVo() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getBlogsId() {
		return blogsId;
	}
	
	public void setBlogsId(Long blogsId) {
		this.blogsId = blogsId;
	}
	
	public Long getTagsId() {
		return tagsId;
	}
	
	public void setTagsId(Long tagsId) {
		this.tagsId = tagsId;
	}
	
}
