package com.buffll.service.impl;

import com.buffll.dao.CommentDao;
import com.buffll.entity.Comment;
import com.buffll.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 评论功能的业务层实现类
 * @author pxz
 * @create 2021-03-09 16:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {
	@Autowired(required = false)
	private CommentDao commentDao;
	
	@Override
	public Integer getCommentCountByBlogId(Long blogId) {
		return commentDao.getCommentCountByBlogId(blogId);
	}
	
	@Override
	public List<Comment> getCommentByBlogId(Long blogId) {
		//查询父评论
		//没有父节点的默认为-1
		List<Comment> comments = commentDao.getCommentByBlogId(blogId, Long.parseLong("-1"));
		for (Comment comment : comments) {
			comment.setReplyComments(commentDao.getSecondaryCommentBySelfId(comment.getId()));
		}
		return comments;
	}
	
	@Override
	public Integer saveComment(Comment comment) {   //接收回复的表单
		//防止输入集合为null
		if (comment.getParentCommentId() != null) {
			comment.setParentComment(commentDao.findById(comment.getParentCommentId()));
		}
		
		//Union-Find算法（Union操作），若父级评论不是顶级，则向上迭代找到顶级评论作为父评论，只改Id，不改父亲name
		Long curId = comment.getParentComment().getId();
		if (curId != -1) {
			while (commentDao.findById(curId).getParentCommentId() != -1) {
				curId = commentDao.findById(curId).getParentCommentId();
			}
		}
		//Union更新
		comment.setParentCommentId(curId);
		if (curId == -1){
			comment.setParentComment(null);
		}
		else{
			comment.setParentComment(commentDao.findById(curId));
		}
		//能走到这，说明ParentCommentId和ParentComment已经初始化好了
		comment.setCreateTime(new Date());
		return commentDao.saveComment(comment);
	}
	
	@Override
	public void deleteComment(Comment comment) {
		//如果是顶级评论，先删除其子评论，再删除自己
		List<Comment> childComments = commentDao.getSecondaryCommentBySelfId(comment.getId());
		for (Comment childComment : childComments) {
			commentDao.deleteComment(childComment);
		}
		commentDao.deleteComment(comment);
	}
}
