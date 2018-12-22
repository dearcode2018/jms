/**
  * @filename ShangHaiConsumer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @type ShangHaiConsumer
 * @description 
 * @author qianye.zheng
 */

public class ShangHaiConsumer implements MessageListener
{

	/**
	 * @description 
	 * @param message
	 * @author qianye.zheng
	 */
	@Override
	public void onMessage(Message message)
	{
		System.out.println("ShangHaiConsumer.onMessage(), message: " + message);
	}

}
