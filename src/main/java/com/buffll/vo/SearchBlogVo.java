package com.buffll.vo;

import java.io.Serializable;

/**
 * 博客搜索的值对象
 * @author pxz
 * @create 2021-03-11 10:46
 */
public class SearchBlogVo implements Serializable {
	/**
	 * 博客标题
	 */
	private String title;
	
	/**
	 * 是否推荐
	 */
	private Boolean recommend;
	
	/**
	 * 博客所属的分类的id
	 */
	private Long typeId;
	
	public SearchBlogVo() {
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Boolean getRecommend() {
		return recommend;
	}
	
	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}
	
	public Long getTypeId() {
		return typeId;
	}
	
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}
