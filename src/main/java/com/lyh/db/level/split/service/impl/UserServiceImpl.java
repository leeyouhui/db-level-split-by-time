package com.lyh.db.level.split.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.db.level.split.dao.UserDao;
import com.lyh.db.level.split.domain.User;
import com.lyh.db.level.split.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public int delete(User user) {
		return userDao.delete(user);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public User selectById(User user) {
		return userDao.selectById(user);
	}

	@Override
	public int countUser(Map<String, Object> param) {
		return userDao.countUser(param);
	}

	@Override
	public List<User> listPageUser(Map<String, Object> param) {
		return userDao.listPageUser(param);
	}

	@Override
	public int createNewTable(String tableName) {
		return userDao.createNewTable(tableName);
	}

	@Override
	public int isExistsTable(String tableName) {
		return userDao.isExistsTable(tableName);
	}

}
