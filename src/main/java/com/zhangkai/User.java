/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: User.java 
 * @Prject: zhangkai-redis
 * @Package: com.zhangkai 
 * @Description: TODO
 * @author: 张凯   
 * @date: 2019年10月12日 上午9:04:11 
 * @version: V1.0   
 */
package com.zhangkai;

import java.io.Serializable;

/** 
 * @ClassName: User 
 * @Description: TODO
 * @author: 张凯
 * @date: 2019年10月12日 上午9:04:11  
 */
public class User implements Serializable{

/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	//	1.编写一个User类，共有ID，姓名，性别，手机，邮箱，生日，共6个属性。
	private int id;
	private String name;
	private String sex;
	private String phone;
	private String email;
	private String birth;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", email=" + email
				+ ", birth=" + birth + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
