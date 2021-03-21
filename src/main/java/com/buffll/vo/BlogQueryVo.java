package com.buffll.vo;

import com.buffll.entity.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * 显示博客数据的值对象
 * @author pxz
 * @create 2021-02-26 17:15
 */
public class BlogQueryVo implements Serializable {
	// vo 包: 主要用于传输数据,用于向页面返回数据,值对象一般放在这个包下
	
	private Long id;
	
	/**
	 * 博客标题
	 */
	private String title;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 是否推荐
	 */
	private Boolean recommend;
	
	/**
	 * 是否发布
	 */
	private Boolean published;
	
	/**
	 * 博客所属分类的id
	 */
	private Long typeId;
	
	/**
	 * 博客所属分类
	 */
	private Type type;
	
	public BlogQueryVo() {
	}
	
	public BlogQueryVo(Long id, String title, Date updateTime, Boolean recommend, Boolean published, Long typeId, Type type) {
		this.id = id;
		this.title = title;
		this.updateTime = updateTime;
		this.recommend = recommend;
		this.published = published;
		this.typeId = typeId;
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Boolean getRecommend() {
		return recommend;
	}
	
	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}
	
	public Boolean getPublished() {
		return published;
	}
	
	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	public Long getTypeId() {
		return typeId;
	}
	
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
}
