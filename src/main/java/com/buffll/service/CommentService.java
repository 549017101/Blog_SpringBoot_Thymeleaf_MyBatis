package com.buffll.service;

import com.buffll.entity.Comment;

import java.util.List;

/**
 * 评论功能的业务层接口
 * @author pxz
 * @create 2021-03-01 16:30
 */
public interface CommentService {
	
	/**
	 * 获取评论数据,并根据创建时间倒序排序
	 * @param blogId 当前博客id
	 * @return
	 */
	List<Comment> getCommentByBlogId(Long blogId);
	
	/**
	 * 根据博客id查询当前博客的总评论数
	 * @param blogId 博客id
	 * @return
	 */
	Integer getCommentCountByBlogId(Long blogId);
	
	/**
	 * 保存评论信息
	 * @param comment
	 * @return
	 */
	Integer saveComment(Comment comment);
	
	/**
	 * 删除评论
	 * @param comment
	 */
	void deleteComment(Comment comment);
}
