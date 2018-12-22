/**
  * @filename Consumer.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.message;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

 /**
 * @type Consumer
 * @description 消息消费者
 * @author qye.zheng
 */
public final class Consumer extends BaseMessage
{
	
	private static transient ConnectionFactory factory;
	
	private transient Connection connection;
	
	private transient Session session;
	
	private MessageConsumer messageConsumer;
	
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public Consumer(final String queueName, final MessageListener listener)
	{
		factory = new ActiveMQConnectionFactory(BROKER_URL);
		try
		{
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建队列
			final Queue queue = session.createQueue(queueName);
			// 创建消息消费者
			messageConsumer = session.createConsumer(queue);
			// 设置消息接收监听器
			messageConsumer.setMessageListener(listener);
		} catch (JMSException e)
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
