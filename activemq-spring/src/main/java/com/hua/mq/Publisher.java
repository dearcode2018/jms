/**
  * @filename Publisher.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.mq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;


 /**
 * @type Publisher
 * @description  
 * @author qye.zheng
 */
public class Publisher
{
	protected int MAX_DELTA_PERCENT = 1;
	
	protected Map<String, Double> LAST_PRICES = new HashMap<String, Double>();
	
	protected static String brokerUrl = "tcp://127.0.0.1:61616";
	
	protected static transient ConnectionFactory factory;
	
	protected transient Connection connection;
	
	protected transient Session session;
	
	protected transient MessageProducer producer;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public Publisher()
	{
		factory = new ActiveMQConnectionFactory(brokerUrl);
		try
		{
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(null);
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
	 * 
	 * @description 
	 * @param stocks
	 * @author qye.zheng
	 */
	public void sendMessage(final String[] stocks)
	{
		int idx = 0;
		wh:
		while (true)
		{
			idx = (int) Math.round(stocks.length * Math.random());
			if (idx < stocks.length)
			{
				break wh;
			}
		}
		String stock = stocks[idx];
		try
		{
			final Destination destination = session.createTopic("STOCKS." + stock);
			final Message message = createStockMessage(stock, session);
		      System.out.println("Sending: " + ( (ActiveMQMapMessage) message).getContentMap() 
		    		  + " on destination: " + destination);
		      producer.send(destination, message);
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @description 
	 * @param stock
	 * @param session
	 * @return
	 * @author qye.zheng
	 */
	protected Message createStockMessage(final String stock, final Session session)
	{
		Double value = LAST_PRICES.get(stock);
		if (null == value)
		{
			value = new Double(Math.random() * 100);
		}
		double oldPrice = value.doubleValue();
		value = new Double(mutatePrice(oldPrice));
		LAST_PRICES.put(stock, value);
		double price = value.doubleValue();
		double offer = price * 1.001;
		boolean up = (price > oldPrice);
		MapMessage mapMessage = null;
		try
		{
			mapMessage = session.createMapMessage();
			mapMessage.setString("stock", stock);
			mapMessage.setDouble("price", price);
			mapMessage.setDouble("offer", offer);
			mapMessage.setBoolean("up", up);
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
		
		return mapMessage;
	}
	
	/**
	 * 
	 * @description 
	 * @param price
	 * @return
	 * @author qye.zheng
	 */
	protected double mutatePrice(final double price)
	{
		double percentChange = (2 * Math.random() * MAX_DELTA_PERCENT) 
				- MAX_DELTA_PERCENT;
		
		 return price * (100 + percentChange) / 100;  
	}
	
}
