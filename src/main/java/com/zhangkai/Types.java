/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Tyes.java 
 * @Prject: zhangkai-redis
 * @Package: com.zhangkai 
 * @Description: TODO
 * @author: 张凯   
 * @date: 2019年10月11日 下午6:45:26 
 * @version: V1.0   
 */
package com.zhangkai;

import java.io.Serializable;

/** 
 * @ClassName: Tyes 
 * @Description: TODO
 * @author: 张凯
 * @date: 2019年10月11日 下午6:45:26  
 */
public class Types implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Types [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
