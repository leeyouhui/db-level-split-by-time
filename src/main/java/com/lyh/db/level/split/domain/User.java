package com.lyh.db.level.split.domain;

import java.util.Date;

/**
 * 实体
 * @FileName:User.java 
 * @Author: liyouhui
 * @Date: 2016年6月6日 上午11:02:54
 * @since:  JDK 1.8
 */
public class User extends BaseDomain{
	private String id;
	private String name;
	private int sex;
	private int age;
	private String tel;
	private String addr;
	private String dsc;
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", age="
				+ age + ", tel=" + tel + ", addr=" + addr + ", dsc=" + dsc
				+ ", createTime=" + createTime + "]";
	}
	
}
