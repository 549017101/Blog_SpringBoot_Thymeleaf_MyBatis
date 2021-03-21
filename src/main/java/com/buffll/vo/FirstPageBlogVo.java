package com.buffll.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目首页显示的博客的值对象
 * @author pxz
 * @create 2021-03-12 10:18
 */
public class FirstPageBlogVo implements Serializable {
	
	private Long id;
	
	/**
	 * 博客标题
	 */
	private String title;
	
	/**
	 * 首图
	 */
	private String firstPicture;
	
	/**
	 * 浏览量
	 */
	private Integer views;
	
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
	 * 博客描述
	 */
	private String description;
	
	/**
	 * 分类名称
	 */
	private String typeName;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像
	 */
	private String avatar;
	
	public FirstPageBlogVo() {
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
	
	public String getFirstPicture() {
		return firstPicture;
	}
	
	public void setFirstPicture(String firstPicture) {
		this.firstPicture = firstPicture;
	}
	
	public Integer getViews() {
		return views;
	}
	
	public void setViews(Integer views) {
		this.views = views;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
}
