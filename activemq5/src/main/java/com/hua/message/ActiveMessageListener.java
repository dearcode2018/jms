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
import javax.jms.TextMessage;

 /**
 * @type ActiveMessageListener
 * @description  
 * @author qye.zheng
 */
public final class ActiveMessageListener implements MessageListener
{

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
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
	}

}
