/**
 * 描述: 
 * BaseTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.hua.log.BaseLog;

/**
 * 描述: 测试基类
 * 包含多个测试示例
 * 
 * @author qye.zheng
 * BaseTest
 */
//@RunWith(JUnitPlatform.class)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("BaseTest")
public class BaseTest extends BaseLog {
	
	/** 代理地址  */
	protected static final String BROKER_URL = "tcp://192.168.5.2:61616";
	
	/** 连接MQ服务器用户名 */
	protected static final String USERNAME = "activemq";
	
	/** 连接MQ服务器密码 */
	protected static final String PASSWORD = "activemq";
	
	/** 队列名称 */
	protected static final String QUEUE_NAME = "myQueue";
	
	/** 主题名称 */
	protected static final String TOPIC_NAME = "myTopic";
	
	/**
	 * 
	 * 描述: [所有测试]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeClass")
	@BeforeAll
	public static void beforeClass() {
		System.out.println("beforeClass()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterClass")
	@AfterAll
	public static void afterClass() {
		System.out.println("afterClass()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}

}
