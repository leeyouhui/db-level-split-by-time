package com.lyh.db.level.split.timer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lyh.db.level.split.service.UserService;
import com.lyh.db.level.split.util.UserUtil;

/**
 * 定时器
 * @FileName:UserTimer.java 
 * @Author: liyouhui
 * @Date: 2016年6月6日 下午3:59:24
 * @since:  JDK 1.8
 */
@Component("userTimer")
@Lazy(false)
public class UserTimer {
	
	@Autowired
	private UserService userService;
	
	@Scheduled(cron = "${job.order_user.cron}")
	public void runTask(){
		//建表
		userService.createNewTable(UserUtil.initTableName(new Date()));
		System.out.println("-----------createNewTable success------------");
	}
}
