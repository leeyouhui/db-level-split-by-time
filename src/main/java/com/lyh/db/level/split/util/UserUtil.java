package com.lyh.db.level.split.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.lyh.db.level.split.domain.User;

public class UserUtil {
	private final static String TABLE_PREFIX = "user_";
	@SuppressWarnings("unused")
	@Deprecated
	private static int tableSuffixLength;
	private static long timeInterval;
	private static String splitTableRule;
	private static String defaultMinDate;
	static{
		ResourceBundle rb = ResourceBundle.getBundle("user");
		splitTableRule = rb.getString("split.table.rule");
		timeInterval = Long.parseLong(rb.getString("time.interval"));
		defaultMinDate = rb.getString("default.min.date");
		tableSuffixLength = splitTableRule.replaceAll("_|-| |,|:|：|;|。", "").length();
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
		Object startTimeStr = param.get("startTime");
		Object endTimeStr = param.get("endTime");
		if(StringUtils.isEmpty(startTimeStr) && StringUtils.isEmpty(endTimeStr)){
			return null;
		}
		
		try {
			Date startDate = null;
			Date endDate = null;
			if( ! StringUtils.isEmpty(startTimeStr) && StringUtils.isEmpty(endTimeStr)){
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTimeStr.toString());
				endDate = new Date();
			}else if(StringUtils.isEmpty(startTimeStr) && ! StringUtils.isEmpty(endTimeStr)){
				startDate = new SimpleDateFormat("yyyy-MM-dd").parse(defaultMinDate);
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr.toString());
			}else if( ! StringUtils.isEmpty(startTimeStr) && ! StringUtils.isEmpty(endTimeStr)){
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTimeStr.toString());
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr.toString());
			}
			long startTime = new SimpleDateFormat(splitTableRule).parse(new SimpleDateFormat(splitTableRule).format(startDate)).getTime();
			long endTime = new SimpleDateFormat(splitTableRule).parse(new SimpleDateFormat(splitTableRule).format(endDate)).getTime();
			if(startTime > endTime){
				return null;
			}
			tableNames = new ArrayList<String>();
			for(long periodTime = startTime ; periodTime < endTime;){
				tableNames.add(TABLE_PREFIX + new SimpleDateFormat(splitTableRule).format(new Date(periodTime)));
				periodTime += timeInterval;
			}
			tableNames.add(TABLE_PREFIX + new SimpleDateFormat(splitTableRule).format(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tableNames;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new SimpleDateFormat(splitTableRule).format(new Date(146518560000l)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
