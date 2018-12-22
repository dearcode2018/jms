/**
 * 描述: 
 * RocketMQTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.queue;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * RocketMQTest
 */
public final class RocketMQTest extends BaseTest {

	/* Name Server地址，多个用分号隔开，端口默认 9876 */
	private static final String NAMES_SERVER_ADDR = "127.0.0.1:9876";
	
	private static final String TOPIC_NAME = "fruit_topic";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRocketMQ() {
		try {
			
			
		} catch (Exception e) {
			log.error("testRocketMQ =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 消费者 push 模式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConsumerPush() {
		try {
			// 消费者分组
			String consumerGroup = "consumer1";
			DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
			consumer.setNamesrvAddr(NAMES_SERVER_ADDR);
			/*
			 * 消费策略
			 * CONSUME_FROM_LAST_OFFSET 默认策略，从队列末尾开始消费，即跳过历史消息
			 * CONSUME_FROM_FIRST_OFFSET 从队列开头开始消费，即历史消息全部消费一遍
			 * CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp
			 * 配合使用，默认 半个小时以前.
			 */
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			
			// 订阅消息 , * 表示所有tag
			consumer.subscribe(TOPIC_NAME, "*");
			
			// 注册监听器，接收消息
			consumer.registerMessageListener(new MessageListenerConcurrently()
			{
				/**
				 * 
				 * @description 并行监听
				 * @param msgs
				 * @param context
				 * @return
				 * @author qianye.zheng
				 */
				@Override
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
						ConsumeConcurrentlyContext context)
				{
					System.out.println(Thread.currentThread().getName() + ": " + msgs);
					for (MessageExt e : msgs)
					{
						System.out.println("接收消息: " + new String(e.getBody()));
					}
					/*
					 * 返回状态
					 * CONSUME_SUCCESS 消费陈功
					 * RECONSUME_LATER 消费失败，待会重新消费
					 */
					
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});
			
			// 启动消费者
			consumer.start();
			
			System.out.println("Consumer Started.");
			
			
			// 避免主线程结束
			Thread.sleep(30 * 1000);
			
		} catch (Exception e) {
			log.error("testConsumerPush =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 消费者 pull 模式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConsumerPull() {
		try {
			// 消费者分组
			String consumerGroup = "consumer1";
			DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(consumerGroup);
				
			
		} catch (Exception e) {
			log.error("testConsumerPull =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testProducer() {
		try {
			// 消费者分组
			String producerGroup = "producer1";
			DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
			producer.setNamesrvAddr(NAMES_SERVER_ADDR);
			
			// 启动生产者
			producer.start();
			
			// 发送5次消息
			for (int i = 0; i < 5; i++)
			{
				byte[] body = String.valueOf("hi, 我是生产者 " + i).getBytes();
				Message message = new Message(TOPIC_NAME, "tagA", body);
				/*
				 * 发送消息
				 * 同步发送，直接接收返回值
				 */
				SendResult sendResult = producer.send(message);
				System.out.println(sendResult);
			}
			
			System.out.println("Producer Started.");
			
		} catch (Exception e) {
			log.error("testProducer =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
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
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
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
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
