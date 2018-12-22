/**
  * @filename MessageProducer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.message;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

 /**
 * @type MessageProducer
 * @description 
 * @author qianye.zheng
 */
@Service
public class MessageProducer extends CoreMessageService
{
	private String groupName = "producer1";
	
	private DefaultMQProducer producer;
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void init()
	{
		producer = new DefaultMQProducer(groupName);
		producer.setNamesrvAddr(nameServerAddr);
		try
		{
			// 启动生产者
			producer.start();
			System.out.println("Producer Started.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param text
	 * @author qianye.zheng
	 */
	public void send(final String text)
	{
		final byte[] body = text.getBytes();
		Message message = new Message(topicName, body);
		/*
		 * 发送消息
		 * 同步发送，直接接收返回值
		 */
		SendResult sendResult = null;
		try
		{
			sendResult = producer.send(message);
			System.out.println(sendResult);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
