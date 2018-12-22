/**
  * @filename Consumer.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

 /**
 * @type Consumer
 * @description  
 * @author qye.zheng
 */
public final class Consumer
{
	
	private static String brokerUrl = "tcp://127.0.0.1:61616";
	
	private static transient ConnectionFactory factory;
	
	private transient Connection connection;
	
	private transient Session session;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public Consumer()
	{
		factory = new ActiveMQConnectionFactory(brokerUrl);
		try
		{
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @author qye.zheng
	 */
	public void close()
	{
		if (null != connection)
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

	/**
	 * @return the brokerUrl
	 */
	public static final String getBrokerUrl()
	{
		return brokerUrl;
	}

	/**
	 * @return the factory
	 */
	public static final ConnectionFactory getFactory()
	{
		return factory;
	}

	/**
	 * @return the connection
	 */
	public final Connection getConnection()
	{
		return connection;
	}

	/**
	 * @return the session
	 */
	public final Session getSession()
	{
		return session;
	}
	
}
