/**
 * 描述: 
 * ConsumerTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.message;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.message.ActiveMessageListener;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ConsumerTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class ConsumerTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testConsumer() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
			// 使用用户和密码连接
			Connection connection = factory.createConnection(USERNAME, PASSWORD);
			//Connection connection = factory.createConnection();
			// 开始连接
			connection.start();
			// 是否使用本地事务
			boolean transacted = true;
			/*
			 * 是否使用本地事务，确认模式
			 */
			Session session = connection.createSession(transacted, Session.SESSION_TRANSACTED);
			// 创建队列
			Queue queue = session.createQueue(QUEUE_NAME);
			// 基于队列创建消费者
			MessageConsumer consumer = session.createConsumer(queue);
			// 文本消息
			/*
			 * recevie()为阻塞方法，若没有消息会一直阻塞直到有消息到来
			 * 用监听器的方式可以转变为异步方式
			 */
			TextMessage message = (TextMessage) consumer.receive();
			System.out.println("receive = " + message.getText());
			// 确认接收到
			message.acknowledge();
			
			/*
			 * 提交了事务，确认收到消息
			 * acknowledge()方法可以不调用
			 */
			session.commit();
			
			
		} catch (Exception e) {
			log.error("testConsumer =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testQueueConsumer() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
			// 使用用户和密码连接
			Connection connection = factory.createConnection(USERNAME, PASSWORD);
			//Connection connection = factory.createConnection();
			// 开始连接
			connection.start();
			// 是否使用本地事务
			boolean transacted = true;
			/*
			 * 是否使用本地事务，确认模式
			 */
			Session session = connection.createSession(transacted, Session.SESSION_TRANSACTED);
			// 创建队列
			Queue queue = session.createQueue(QUEUE_NAME);
			// 基于队列创建消费者
			MessageConsumer consumer = session.createConsumer(queue);
			// 文本消息
			TextMessage message = (TextMessage) consumer.receive();
			System.out.println("receive = " + message.getText());
			// 确认接收到
			//message.acknowledge();
			
			/*
			 * 提交了事务，确认收到消息
			 * 消息将从服务端队列中移除
			 * acknowledge()方法可以不调用
			 */
			session.commit();
			
		} catch (Exception e) {
			log.error("testQueueConsumer =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testTopicConsumer() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
			// 使用用户和密码连接
			Connection connection = factory.createConnection(USERNAME, PASSWORD);
			//Connection connection = factory.createConnection();
			// 开始连接
			connection.start();
			// 是否使用本地事务
			boolean transacted = true;
			/*
			 * 是否使用本地事务，确认模式
			 */
			Session session = connection.createSession(transacted, Session.SESSION_TRANSACTED);
			// 创建主题
			Topic topic = session.createTopic(TOPIC_NAME);
			// 基于主题创建消费者
			MessageConsumer consumer = session.createConsumer(topic);
			/*
			 * 接收消息，可设置超时时间
			 * 订阅的消息，只有开启订阅之后，生产者发布的消息才能
			 * 收到，在此之前生产者发布的消息不会再收到.
			 * 
			 */
			TextMessage message = (TextMessage) consumer.receive();
			System.out.println("receive = " + message.getText());
			
			// 确认接收到
			message.acknowledge();
			
			/*
			 * 提交了事务，确认收到消息
			 * 消息将从服务端队列中移除
			 * acknowledge()方法可以不调用
			 */
			session.commit();
			
		} catch (Exception e) {
			log.error("testTopicConsumer =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testConsumerListener() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
			// 使用用户和密码连接
			Connection connection = factory.createConnection(USERNAME, PASSWORD);
			//Connection connection = factory.createConnection();
			// 开始连接
			connection.start();
			// 是否使用本地事务
			boolean transacted = true;
			/*
			 * 是否使用本地事务，确认模式
			 */
			Session session = connection.createSession(transacted, Session.SESSION_TRANSACTED);
			// 创建队列
			Queue queue = session.createQueue(QUEUE_NAME);
			// 基于队列创建消费者
			MessageConsumer consumer = session.createConsumer(queue);
			// 文本消息
			/*
			 * recevie()为阻塞方法，若没有消息会一直阻塞直到有消息到来
			 * 用监听器的方式可以转变为异步方式
			 */
			// 注册监听器
			consumer.setMessageListener(new ActiveMessageListener(session));

			// 继续做其他事情
			Thread.sleep(100 * 1000);
			/*
			 * 提交了事务，确认收到消息
			 * acknowledge()方法可以不调用
			 */
			//session.commit();
			
			
		} catch (Exception e) {
			log.error("testConsumerListener =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testUnsubscribe() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
			// 使用用户和密码连接
			Connection connection = factory.createConnection(USERNAME, PASSWORD);
			//Connection connection = factory.createConnection();
			// 开始连接
			connection.start();
			// 是否使用本地事务
			boolean transacted = true;
			/*
			 * 是否使用本地事务，确认模式
			 */
			Session session = connection.createSession(transacted, Session.SESSION_TRANSACTED);
			// 创建主题
			Topic topic = session.createTopic(TOPIC_NAME);
			
			
			
		} catch (Exception e) {
			log.error("testUnsubscribe =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testTemp")
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testCommon")
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testSimple")
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
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
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
		assumeFalse(false);
		assumeTrue(true);
		assumingThat(true, null);
	}

}
