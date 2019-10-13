package com.zhangkai;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zhangkai.utils.DateUtil;
import com.zhangkai.utils.PerformanceUtils;
import com.zhangkai.utils.PerformanceUtils.TestMethod;
import com.zhangkai.utils.RandomUtil;
import com.zhangkai.utils.StringUtil;

/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: RedisTest.java 
 * @Prject: zhangkai-redis
 * @Package:  
 * @Description: TODO
 * @author: 张凯   
 * @date: 2019年10月11日 下午1:44:14 
 * @version: V1.0   
 */

/** 
 * @ClassName: RedisTest 
 * @Description: TODO
 * @author: 张凯
 * @date: 2019年10月11日 下午1:44:14  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class RedisTest {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * @Title: redisTest 
	 * @Description: 普通测试
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void redisTest() {
		
		
		redisTemplate.opsForValue().append("name3", "zhangkai");
		String string = (String) redisTemplate.opsForValue().get("name3");
		System.out.println(string);

//		redisTemplate.opsForValue().set("name3","zhangkai");
//        String name3 = (String) redisTemplate.opsForValue().get("name3");
//        System.out.println(name3);
	}
	
	/**
	 * @Title: redisTest 
	 * @Description: 测试List
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void redisList() {
		
		redisTemplate.opsForList().leftPush("mylist", "a");
		//pop就是弹出一个  就删除一个
		String ele = (String) redisTemplate.opsForList().rightPop("mylist");
		System.out.println(ele);
		
	}
	
	
	/**
	 * @Title: redisTest 
	 * @Description: 测试List对象
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void redisListObject() {
		
		List<Types> list = new ArrayList<Types>();
		
		Types types1 = new Types();
		types1.setId(1);
		types1.setName("电器");

		Types types2 = new Types();
		types2.setId(2);
		types2.setName("图书");

		Types types3 = new Types();
		types3.setId(5);
		types3.setName("水果");
		
		list.add(types1);
		list.add(types2);
		list.add(types3);
		
		redisTemplate.opsForList().leftPush("mylist", list);
		//pop就是弹出一个  就删除一个
		List<Types> ele = (List<Types>) redisTemplate.opsForList().rightPop("mylist");
		
		for (Types types : ele) {
			
			System.out.println(types);
		}
		
	}
	
	
	/**
	 * @Title: redisTest 
	 * @Description: 测试hash
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void redisHash() {
		
		Map<String, Types> map = new HashMap<String, Types>();
		
		Types types1 = new Types();
		types1.setId(1);
		types1.setName("电器");

		Types types2 = new Types();
		types2.setId(2);
		types2.setName("图书");

		Types types3 = new Types();
		types3.setId(5);
		types3.setName("水果");
		
		map.put(1 + "", types1);
		map.put(2 + "", types2);
		map.put(3 + "", types3);
		
		redisTemplate.opsForHash().putAll("myhash", map);
		
		Map entries = redisTemplate.opsForHash().entries("myhash");
		
		Set keySet = entries.entrySet();
		
		for (Object object : keySet) {
			
			System.out.println(object);
			
		}
		
	}
	
	/**
	 * @Title: redisTest 
	 * @Description: 测试Set
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void redisSet() {
		
		 List<Types> types = new ArrayList<Types>();
		 
         Types types1 = new Types();
         types1.setId(1);
         types1.setName("电器");

         Types types2 = new Types();
         types2.setId(2);
         types2.setName("图书");

         Types types3 = new Types();
         types3.setId(5);
         types3.setName("水果");

         types.add(types1);
         types.add(types2);
         types.add(types3);

		redisTemplate.opsForSet().add("myset", types);
		
		Set set = redisTemplate.opsForSet().members("myset");
		
		for (Object object : set) {
			System.out.println(object);
		}
		
	}

	
	/**
	 * @Title: redisTest 
	 * @Description: 测试Json
	 *    把字符串  转 json 把  json 转  字符串
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void redisJson() {
		
		Types types1 = new Types();
		types1.setId(1);
		types1.setName("电器");
		
		String string = JSON.toJSONString(types1);
		
		redisTemplate.opsForValue().set("string", string);
		
		String object = (String) redisTemplate.opsForValue().get("string");
		
		System.out.println(object);
		
		Types parse = JSON.parseObject(object,Types.class);
		System.out.println(parse);
		
		
		
	}
	
	
	
//--------------------------------第二周周考----------------------------------------
	
	private String sex[] = {"男","女"}; 
	private String type[] = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com"}; 
	private String str ="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
//	2.模拟生成十万个user对象，所有的属性值不能相同。
	@SuppressWarnings("unchecked")
	@Test
	public void redisUser() {
		
		for (int i = 0; i < 10; i++) {
			//new user  对象
			User user = new User();
			//	(1)ID使用1-10万序号。（2分）
			//  I  的值  是 id 的  序号值
			user.setId(i);
				
	//	(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			//调用工具类
			String name = StringUtil.generateChineseName();
			user.setName(name);
	//	(3)性别在女和男两个值中随机。（4分）
			//调用工具类
			int j = RandomUtil.random(0, sex.length-1);
			user.setSex(sex[j]);
	//	(4)手机以13开头+9位随机数模拟。（4分）
			//调用工具类
			String phone ="13";
			//9个数字
			for (int k = 0; k < 9; k++) {
				int l = RandomUtil.random(0, 9);
				phone += l;
			}
			user.setPhone(phone);
	//	(5)邮箱以3-20个随机字母+@qq.com、@163.com、@sian.com、@gmail.com、@sohu.com、@hotmail.com模拟。（4分）
			int m = RandomUtil.random(3, 20);
			String string = RandomUtil.randomString(m);
			int t = RandomUtil.random(0, type.length-1);
			String email = string + type[t];
			user.setEmail(email);
	//	(6)生日要模拟18-70岁之间。（4分）
			Date date = DateUtil.getRandomDate(18, 70);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String string2 = format.format(date);
			user.setBirth(string2);
			System.out.println(user);
		}
		
	}
	
	
	
	
//	3.使用JDK系列化方式将十万个user对象保存到Redis，测试所耗时间。
	@SuppressWarnings("unchecked")
	@Test
	public void redisJDK() {
		
		PerformanceUtils.testPerformance(new TestMethod() {
			
			public void doMethod() {
				// TODO Auto-generated method stub
				
				for (int i = 0; i < 10; i++) {
					//new user  对象
					User user = new User();
					//	(1)ID使用1-10万序号。（2分）
					//  I  的值  是 id 的  序号值
					user.setId(i);
						
			//	(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
					//调用工具类
					String name = StringUtil.generateChineseName();
					user.setName(name);
			//	(3)性别在女和男两个值中随机。（4分）
					//调用工具类
					int j = RandomUtil.random(0, sex.length-1);
					user.setSex(sex[j]);
			//	(4)手机以13开头+9位随机数模拟。（4分）
					//调用工具类
					String phone ="13";
					//9个数字
					for (int k = 0; k < 9; k++) {
						int l = RandomUtil.random(0, 9);
						phone += l;
					}
					user.setPhone(phone);
			//	(5)邮箱以3-20个随机字母+@qq.com、@163.com、@sian.com、@gmail.com、@sohu.com、@hotmail.com模拟。（4分）
					int m = RandomUtil.random(3, 20);
					String string = RandomUtil.randomString(m);
					int t = RandomUtil.random(0, type.length-1);
					String email = string + type[t];
					user.setEmail(email);
			//	(6)生日要模拟18-70岁之间。（4分）
					Date date = DateUtil.getRandomDate(18, 70);
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String string2 = format.format(date);
					user.setBirth(string2);
					System.out.println(user);
					
					redisTemplate.opsForValue().set("user"+i, user);
					User object = (User) redisTemplate.opsForValue().get("user"+i);
					System.out.println("取出的 ++"+object);
					
				}
				
			}
		}, false);
		
	}
//	4.使用JSON系列化方式将十万个user对象保存到Redis，测试所耗时间。
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void redisJSON() {
		
		
		PerformanceUtils.testPerformance(new TestMethod() {//匿名内部类

			public void doMethod() {
				// TODO Auto-generated method stub
				
				for (int i = 0; i < 1000; i++) {
					//new user  对象
					User user = new User();
					//	(1)ID使用1-10万序号。（2分）
					//  I  的值  是 id 的  序号值
					user.setId(i);
					
					//	(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
					//调用工具类
					String name = StringUtil.generateChineseName();
					user.setName(name);
					//	(3)性别在女和男两个值中随机。（4分）
					//调用工具类
					int j = RandomUtil.random(0, sex.length-1);
					user.setSex(sex[j]);
					//	(4)手机以13开头+9位随机数模拟。（4分）
					//调用工具类
					String phone ="13";
					//9个数字
					for (int k = 0; k < 9; k++) {
						int l = RandomUtil.random(0, 9);
						phone += l;
					}
					user.setPhone(phone);
					//	(5)邮箱以3-20个随机字母+@qq.com、@163.com、@sian.com、@gmail.com、@sohu.com、@hotmail.com模拟。（4分）
					int m = RandomUtil.random(3, 20);
					String string = RandomUtil.randomString(m);
					int t = RandomUtil.random(0, type.length-1);
					String email = string + type[t];
					user.setEmail(email);
					//	(6)生日要模拟18-70岁之间。（4分）
					Date date = DateUtil.getRandomDate(18, 70);
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String string2 = format.format(date);
					user.setBirth(string2);
					System.out.println(user);
					
//					使用JSON系列化方式将十万个user对象保存到Redis，测试所耗时间。
					String u = JSON.toJSONString(user);
					
					redisTemplate.opsForValue().set("user"+i, u);
					System.out.println("-------：取出user对象");
					String object = (String) redisTemplate.opsForValue().get("user"+i);
					
					System.out.println(object);
					
					//测试  耗时多少秒
					
				}
				
				
			}
		}, true);//  true 纳秒    false 毫秒
		
		
		
		
	}
	
//	5.使用Redis的Hash类型保存十万个user对象，测试所耗时间。
	
	@SuppressWarnings("unchecked")
	@Test
	public void redisHashTest() {
		
		PerformanceUtils.testPerformance(new TestMethod() {
			
			public void doMethod() {
				// TODO Auto-generated method stub
				
				Map<String, User> map = new HashMap<String, User>();
				
				for (int i = 0; i < 10; i++) {
					//new user  对象
					User user = new User();
					//	(1)ID使用1-10万序号。（2分）
					//  I  的值  是 id 的  序号值
					user.setId(i);
						
			//	(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
					//调用工具类
					String name = StringUtil.generateChineseName();
					user.setName(name);
			//	(3)性别在女和男两个值中随机。（4分）
					//调用工具类
					int j = RandomUtil.random(0, sex.length-1);
					user.setSex(sex[j]);
			//	(4)手机以13开头+9位随机数模拟。（4分）
					//调用工具类
					String phone ="13";
					//9个数字
					for (int k = 0; k < 9; k++) {
						int l = RandomUtil.random(0, 9);
						phone += l;
					}
					user.setPhone(phone);
			//	(5)邮箱以3-20个随机字母+@qq.com、@163.com、@sian.com、@gmail.com、@sohu.com、@hotmail.com模拟。（4分）
					int m = RandomUtil.random(3, 20);
					String string = RandomUtil.randomString(m);
					int t = RandomUtil.random(0, type.length-1);
					String email = string + type[t];
					user.setEmail(email);
			//	(6)生日要模拟18-70岁之间。（4分）
					Date date = DateUtil.getRandomDate(18, 70);
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String string2 = format.format(date);
					user.setBirth(string2);
					System.out.println(user);
					
					map.put("user"+i, user);
					
					redisTemplate.opsForHash().putAll("user", map);
					Map entries = redisTemplate.opsForHash().entries("user"+i);
					
					Set set = entries.entrySet();
					
					for (Object object2 : set) {
						
						System.out.println("取出的 ++"+object2);
					}
					
					
				}
				
			}
		}, false);
		
	}
	
	
	
//	6.每项测试之前和测试之后都要使用命令清空Redis所有库的数据，使得测试更加准确。
//	
	@Test
	public void pohon() {
		String phone ="13";
		//9个数字
		for (int k = 0; k < 9; k++) {
			int l = RandomUtil.random(0, 9);
			phone += l;
		}
		System.err.println(phone);
		
		
		int m = RandomUtil.random(3, 20);
		String string = RandomUtil.randomString(m);
		int t = RandomUtil.random(0, type.length-1);
		String email = string + type[t];
		
		System.out.println(email);
	}
	
	
	
	
}
