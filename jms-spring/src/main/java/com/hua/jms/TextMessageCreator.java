/**
  * @filename TextMessageCreator.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

 /**
 * @type TextMessageCreator
 * @description 文本消息 构造器
 * 需要创建什么类型的消息，应该具体设计不同类来实现
 * 该类只是创建文本消息，需要创建其他消息 需要定义相应
 * 的消息构造器.
 * @author qye.zheng
 */
public final class TextMessageCreator implements MessageCreator {

	private String message;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public TextMessageCreator() {
	}
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public TextMessageCreator(final String message) {
		this.message = message;
	}
	
	/**
	 * @description 发送什么样的消息 由此方法来设定
	 * @param session
	 * @return
	 * @throws JMSException
	 * @author qye.zheng
	 */
	@Override
	public Message createMessage(final Session session) throws JMSException {
		
		return session.createTextMessage(message);
	}

	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public final void setMessage(String message) {
		this.message = message;
	}

}
