package com.lyh.db.level.split.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import com.lyh.db.level.split.domain.User;

public class UserUtil {
	private final static String TABLE_PREFIX = "user_";
	private static String splitTableRule;
	static{
		ResourceBundle rb = ResourceBundle.getBundle("user");
		splitTableRule = rb.getString("split.table.rule");
	}
	
	public static User createUser() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("name"+new Date().getTime());
		user.setSex(111111);
		user.setAge(24);
		user.setTel("12346789");
		user.setAddr("北京通州");
		user.setDsc("java开发工程师");
		user.setCreateTime(new Date());
		user.setTableName(initTableName(user.getCreateTime()));
		return user;
	}
	
	/**
	 * 初始表名
	 * @Author: liyouhui
	 * @Date：2016年6月6日下午12:46:56
	 * @param date
	 * @return
	 */
	public static String initTableName(Date date){
		return TABLE_PREFIX + new SimpleDateFormat(splitTableRule).format(date);
	}
	
	/**
	 * 根据时间范围匹配表名（多）
	 * @Author: liyouhui
	 * @Date：2016年6月6日下午5:13:59
	 * @param param
	 * @return
	 */
	public static List<String> matchTableNames(Map<String, Object> param){
		List<String> tableNames = null;
		try {
			Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(param.get("startTime").toString());
			Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(param.get("endTime").toString());
			long startTime = startDate.getTime();
			long endTime = endDate.getTime();
			if(startTime > endTime){
				return null;
			}
			tableNames = new ArrayList<String>();
			tableNames.add(TABLE_PREFIX + new SimpleDateFormat(splitTableRule).format(startDate));
			
			tableNames.add(TABLE_PREFIX + new SimpleDateFormat(splitTableRule).format(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tableNames;
	}
	
}
