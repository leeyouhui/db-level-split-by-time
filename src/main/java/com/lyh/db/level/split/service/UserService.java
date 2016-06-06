package com.lyh.db.level.split.service;

import java.util.List;
import java.util.Map;

import com.lyh.db.level.split.domain.User;

public interface UserService {
	int insert(User user);

	int delete(User user);

	int update(User user);

	User selectById(User user);

	int countUser(Map<String, Object> param);

	List<User> listPageUser(Map<String, Object> param);
	
	int createNewTable(String tableName);
}
