/**
  * @filename MessageProducer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.producer;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @type MessageProducer
 * @description 消息生产者
 * @author qianye.zheng
 */
@Service
public class MessageProducer
{

	@Resource(name = "amqpTemplate")
	private AmqpTemplate amqpTemplate;
	
	//@Resource(name = "amqpTemplate2")
	//private AmqpTemplate amqpTemplate2;
	
	public void sendMessage(Object message) throws IOException
	{
		amqpTemplate.convertAndSend("queueTest_1Key", message);
		
		amqpTemplate.convertAndSend("queueTest_1_2Key", message);
		/*
		 * 主题交换机模式: abc.#
		 */
		amqpTemplate.convertAndSend("abc.haha", message);
		
		//amqpTemplate2.convertAndSend("queueTest_2Key", message);
		
	}
	
	
}
