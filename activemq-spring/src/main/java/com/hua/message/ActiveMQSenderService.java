/**
  * @filename ActiveMQSenderService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.message;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

 /**
 * @type ActiveMQSenderService
 * @description 
 * @author qianye.zheng
 */
@Service
public class ActiveMQSenderService
{
	
	/**
	 * JmsTemplate是同步的，因此不用来receive消息
	 * 可以用来发送消息
	 */
	@Resource
	private JmsTemplate jmsTemplate;
	
	@Resource
	private Destination appleQueue;
	
	/**
	 * 
	 * @description 发送消息
	 * @param message
	 * @author qianye.zheng
	 */
	public void send(final String message)
	{
		System.out.println("发送消息: " + message);
		
		/*
		 * 队列、主题都是一种目的 Destination
		 * 
		 */
		jmsTemplate.send(appleQueue, new MessageCreator()
		{
			@Override
			public Message createMessage(Session session) throws JMSException
			{
				return session.createTextMessage(message);
			}
		});
	}
	
	
	
	

}
