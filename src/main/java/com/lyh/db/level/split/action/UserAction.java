package com.lyh.db.level.split.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyh.db.level.split.domain.User;
import com.lyh.db.level.split.service.UserService;
import com.lyh.db.level.split.util.UserUtil;

@Service
public class UserAction {
	@Autowired
	private UserService userService;
	
	public void insert() {
		User user = UserUtil.createUser();
		userService.insert(user);
	}

	public void delete(User user) {
		userService.delete(user);
	}

	public void update(User user) {
		userService.update(user);
	}

	public void selectById(User user) {
		userService.selectById(user);
	}

	public void countUser(Map<String, Object> param) {
		userService.countUser(param);
	}

	public void listPageUser(Map<String, Object> param) {
		userService.listPageUser(param);
	}
	
}
