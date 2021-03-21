package com.buffll.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论的实体类
 * @author pxz
 * @create 2021-02-24 22:37
 */
public class Comment implements Serializable {
	private Long id;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 评论者邮箱
	 */
	private String email;
	
	/**
	 * 评论内容
	 */
	@NotBlank(message = "评论内容不能为空")
	private String content;
	
	/**
	 * 评论者头像
	 */
	private String avatar;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * <p>评论所属的博客<p/>
	 * 一条评论只能包含一个博客
	 */
	private Blog blog;
	
	/**
	 * 评论所属的博客id
	 */
	private Long blogId;
	
	/**
	 * <p>评论回复的子类对象<p/>
	 * 一个子评论对象只有一个离它最近的父评论对象
	 */
	private List<Comment> replyComments = new ArrayList<>();
	
	/**
	 * <p>评论回复的父类对象<p/>
	 * 一个父评论对象可以有多个子评论对象,即可以对一条评论再接着评论
	 */
	private Comment parentComment;
	
	/**
	 * 父级评论的id
	 */
	private Long parentCommentId;
	
	/**
	 * 是否为管理员评论
	 */
	private Boolean adminComment;
	
	public Comment() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Blog getBlog() {
		return blog;
	}
	
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public List<Comment> getReplyComments() {
		return replyComments;
	}
	
	public void setReplyComments(List<Comment> replyComments) {
		this.replyComments = replyComments;
	}
	
	public Comment getParentComment() {
		return parentComment;
	}
	
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	
	public Boolean getAdminComment() {
		return adminComment;
	}
	
	public void setAdminComment(Boolean adminComment) {
		this.adminComment = adminComment;
	}
	
	public Long getBlogId() {
		return blogId;
	}
	
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	
	public Long getParentCommentId() {
		return parentCommentId;
	}
	
	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
}
