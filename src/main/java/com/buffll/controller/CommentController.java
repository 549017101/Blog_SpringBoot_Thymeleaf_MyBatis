package com.buffll.controller;

import com.buffll.entity.Comment;
import com.buffll.entity.User;
import com.buffll.service.BlogService;
import com.buffll.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * 评论功能的控制器
 * @author pxz
 * @create 2021-03-13 15:49
 */
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private BlogService blogService;
	
	/**
	 * 从配置文件中获取评论的默认头像
	 */
	@Value("${comment.avatar}")
	private String avatar;
	
	/**
	 * 返回评论片段
	 * @param blogId
	 * @param model
	 * @return
	 */
	@GetMapping("/comments/{blogId}")
	public String comments(@PathVariable Long blogId, Model model) {
		model.addAttribute("comments", commentService.getCommentByBlogId(blogId));
		model.addAttribute("commentCount", commentService.getCommentCountByBlogId(blogId));
		return "pages/blog :: commentList";
	}
	
	/**
	 * 提交评论信息,包括新增评论与回复某条评论
	 * @param comment
	 * @param session
	 * @return
	 */
	@PostMapping("/comments")
	public String commitComment(Comment comment, HttpSession session) {
		Long blogId = comment.getBlog().getId();
		comment.setBlog(blogService.getBlogById(blogId));
		comment.setBlogId(blogId);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			comment.setAvatar(user.getAvatar());
			comment.setAdminComment(true);
		} else {
			comment.setAvatar(avatar);
		}
		commentService.saveComment(comment);
		return "redirect:/comments/" + blogId;
	}
	
	/**
	 * 删除评论
	 * @param blogId 当前博客id
	 * @param id 评论id
	 * @param comment 评论对象
	 * @return
	 */
	@GetMapping("/comment/{blogId}/{id}/delete")
	public String deleteComment(@PathVariable Long blogId, @PathVariable Long id, Comment comment) {
		commentService.deleteComment(comment);
		return "redirect:/blog/" + blogId;
	}
}
