/**
  * @filename ProduceServiceImpl.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.service.impl;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.hua.jms.TextMessageCreator;
import com.hua.service.ProduceService;

 /**
 * @type ProduceServiceImpl
 * @description 
 * @author qye.zheng
 */
public final class ProduceServiceImpl extends CoreServiceImpl implements
		ProduceService {

	/* spring jms 模板 */
	private JmsTemplate jmsTemplate;
	
	/**
	 * 
	 * @description 
	 * @param destination
	 * @param message
	 * @author qye.zheng
	 */
	@Override
	public final void sendMessage(final Destination destination, final String message)
	{
		// spring 消息构造器
		final MessageCreator messageCreator = new TextMessageCreator(message);
		// 发送消息
		jmsTemplate.send(destination, messageCreator);
	}

	/**
	 * @param jmsTemplate the jmsTemplate to set
	 */
	public final void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
