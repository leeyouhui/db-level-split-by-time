package com.lyh.db.level.split;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyh.db.level.split.domain.User;
import com.lyh.db.level.split.service.UserService;
import com.lyh.db.level.split.service.impl.UserServiceImpl;
import com.lyh.db.level.split.util.UserUtil;

public class UserServiceImplTest {

	private static ApplicationContext context;
	private static UserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("spring.xml");
		userService = context.getBean(UserServiceImpl.class);
	}

	@Test
	public void testInsert() {
		int i = 0;
		while(i < 5){
			User user = UserUtil.createUser();
			System.out.println(userService.insert(user));
			i++;
		}
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testSelectById() throws ParseException {
		User user = new User();
		user.setId("d78cdeba-2eac-4f1d-bd9c-2f4c8265dba5");
		user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH").parse("2016-06-06 15:38:24"));
		user.setTableName(UserUtil.initTableName(user.getCreateTime()));
		user = userService.selectById(user);
		System.out.println(user.toString());
	}

	@Test
	public void testCountUser() {
	}

	@Test
	public void testListPageUser() {
		long startTime = System.currentTimeMillis();
		//查询参数
		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("startTime", "2016-06-06 13:00:51");
		param.put("endTime", "2016-06-07 17:00:22");
		List<User> users = new ArrayList<User>();
		//根据时间范围匹配表名（多）
		List<String> tableNames = UserUtil.matchTableNames(param);
		//判断表是否存在，如果参数tableNames为空或大小为0，则返回该数据库中的所有表
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("dbName", "user");
		param1.put("tableNames", tableNames);
		tableNames = userService.isExistsTables(param1);
		
		if(tableNames != null){
			for(String str : tableNames){
				param.put("tableName", str);
				if(userService.countUser(param) > 0){
					users.addAll(userService.listPageUser(param));
				}
			}
		}
		for(User user : users){
			System.out.println(user.toString());
		}
		System.out.println("---------------"+(System.currentTimeMillis()-startTime) +"ms--------");
	}

	@Test
	public void testCreateNewTable() {
		System.out.println(userService.createNewTable(UserUtil.initTableName(new Date())));
	}

	@Test
	public void isExistsTables(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dbName", "user");
		List<String> tableNames = new ArrayList<String>();
		tableNames.add("user_2016_06_06_13");
		tableNames.add("user_2016_06_06_14");
		tableNames.add("user_2016_06_06_13");
		tableNames.add("user_2016_06_06_15");
		tableNames.add("user_2016_06_06_18");
		param.put("tableNames", tableNames);
		tableNames = userService.isExistsTables(param);
		for(String user : tableNames){
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void startProject() throws IOException {
		System.err.println("------start------");
		System.in.read();
	}
}
