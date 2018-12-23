/**
  * @filename ActiveMessageListener.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

 /**
 * @type ActiveMessageListener
 * @description  
 * @author qye.zheng
 */
public final class ActiveMessageListener implements MessageListener
{

	private Session session;
	
	/**
	 * 
	 * @description 构造方法
	 * @param session
	 * @author qianye.zheng
	 */
	public ActiveMessageListener(final Session session)
	{
		this.session = session;
	}
	
	
	/**
	 * @description 
	 * @param message
	 * @author qye.zheng
	 */
	@Override
	public void onMessage(Message message)
	{
		final TextMessage textMessage = (TextMessage) message;
		try
		{
			String msg = textMessage.getText();
			System.out.println("消费者收到消息: " + msg);
			message.acknowledge();
			// 是否重复投递
			//System.out.println(message.getJMSRedelivered());
			/*
			 * 提交了事务，确认收到消息
			 * acknowledge()方法可以不调用
			 */
			session.commit();
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
	}

}
