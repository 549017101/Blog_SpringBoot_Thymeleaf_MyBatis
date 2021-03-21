package com.buffll.dao;

import com.buffll.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论功能的持久层接口
 * @author pxz
 * @create 2021-03-01 16:31
 */
public interface CommentDao {
	
	/**
	 * 获取评论数据,并根据创建时间倒序排序
	 * @param blogId
	 * @param blogParentId
	 * @return
	 */
	List<Comment> getCommentByBlogId(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);
	
	/**
	 * 查询自我评论
	 * @param id
	 * @return
	 */
	Comment findById(@Param("Id") Long id);
	
	/**
	 * 根据博客id查询当前博客的总评论数
	 * @param blogId
	 * @return
	 */
	Integer getCommentCountByBlogId(@Param("blog_id") Long blogId);
	
	/**
	 * 根据父级评论id查询子评论对象
	 * @param commentId
	 * @return
	 */
	List<Comment> getSecondaryCommentBySelfId(@Param("commentId") Long commentId);
	
	/**
	 * 新增评论
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
