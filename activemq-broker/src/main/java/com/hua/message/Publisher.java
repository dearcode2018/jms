/**
  * @filename Publisher.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.message;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


 /**
 * @type Publisher
 * @description  消息生产者
 * @author qye.zheng
 */
public class Publisher extends BaseMessage
{
	protected int MAX_DELTA_PERCENT = 1;
	
	protected Map<String, Double> LAST_PRICES = new HashMap<String, Double>();
	
	protected static transient ConnectionFactory factory;
	
	protected transient Connection connection;
	
	protected transient Session session;
	
	protected transient MessageProducer producer;
	
	/**
	 * 
	 * @description 构造方法
	 * @param queueName 队列名称 (主题)
	 * @author qianye.zheng
	 */
	public Publisher(final String queueName)
	{
		factory = new ActiveMQConnectionFactory(BROKER_URL);
		try
		{
			connection = factory.createConnection();
			connection.start();
			//session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 指定会话级别
			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			// 创建队列
			final Queue queue = session.createQueue(queueName);
			// 创建消息生产者
			producer = session.createProducer(queue);
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param message
	 * @author qye.zheng
	 */
	public void send(final String message)
	{
		try
		{
			// 组装文本消息
			final TextMessage textMessage = session.createTextMessage(message);
			// 发送消息
			producer.send(textMessage);
			// 提交事务
			session.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void close()
	{
		try
		{
			connection.close();
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
	}

}
