/**
  * @filename ConsumeMessageListener.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

 /**
 * @type ConsumeMessageListener
 * @description  消息接收监听器
 * 监听目的地的消息接受者，一旦接收到消息，则触发onMessage方法的调用
 * @author qye.zheng
 */
public final class ConsumeMessageListener implements MessageListener
{

	/**
	 * @description 此方法用来 让消息到达接受者，让接受者作出响应
	 * @param message
	 * @author qye.zheng
	 */
	@Override
	public void onMessage(Message message)
	{
		final TextMessage textMessage = (TextMessage) message;
		try
		{
			System.out.println("接收到纯文本消息.");
			System.out.println("消息内容: " + textMessage.getText());
		} catch (JMSException e)
		{
			e.printStackTrace();
		}  
	}

}
