package com.buffll.service.impl;

import com.buffll.dao.UserDao;
import com.buffll.entity.User;
import com.buffll.exception.NotFoundException;
import com.buffll.service.UserService;
import com.buffll.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户功能的业务层实现类
 * @author pxz
 * @create 2021-03-09 16:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	@Autowired(required = false)
	private UserDao userDao;
	
	@Override
	public User findAvatarAndNickname(Long userId) {
		return userDao.findAvatarAndNickname(userId);
	}
	
	@Override
	public User checkUser(String username, String password) {
		return userDao.findByUsernameAndPassword(username, Md5Utils.code(password));
	}
	
	@Override
	public User changePassword(String username, String oldpwd, String newpwd) {
		User user = userDao.findByUsernameAndPassword(username, Md5Utils.code(oldpwd));
		if (user != null) {
			userDao.changePassword(username, Md5Utils.code(oldpwd), Md5Utils.code(newpwd));
			return null;
		} else {
			throw new NotFoundException("该用户不存在");
		}
	}
}
