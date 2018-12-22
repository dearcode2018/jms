/**
  * @filename GuangZhouConsumer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @type GuangZhouConsumer
 * @description 
 * @author qianye.zheng
 */

public class GuangZhouConsumer implements MessageListener
{

	/**
	 * @description 
	 * @param message
	 * @author qianye.zheng
	 */
	@Override
	public void onMessage(Message message)
	{
		
		System.out.println("GuangZhouConsumer.onMessage(), message: " + message);
		
	}

}
