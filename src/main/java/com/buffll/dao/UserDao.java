package com.buffll.dao;

import com.buffll.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户持久层接口
 * @author pxz
 * @create 2021-02-25 11:25
 */
public interface UserDao {
	
	/**
	 * 根据id获取用户的头像与昵称
	 * @param userId 用户id
	 * @return
	 */
	User findAvatarAndNickname(Long userId);

	/**
	 * 根据用户名和密码查询数据库
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户对象
	 */
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	/**
	 * 修改密码
	 * @param username 要修改密码的用户名
	 * @param oldpwd   旧密码
	 * @param newpwd   新密码
	 * @return 受影响的行数
	 */
	Integer changePassword(@Param("username")String username, String oldpwd, @Param("password")String newpwd);
}
