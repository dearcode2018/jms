/**
  * @filename ActiveMQListener.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.mq;

import java.text.DecimalFormat;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

 /**
 * @type ActiveMQListener
 * @description  
 * @author qye.zheng
 */
public final class ActiveMQListener implements MessageListener
{

	/**
	 * @description 
	 * @param message
	 * @author qye.zheng
	 */
	@Override
	public void onMessage(Message message)
	{
		final MapMessage mapMessage = (MapMessage) message;
        double price = 0.0;
		try
		{
			String stock = mapMessage.getString("stock");  
			price = mapMessage.getDouble("price");
			double offer = mapMessage.getDouble("offer");  
			boolean up = mapMessage.getBoolean("up");  
			DecimalFormat df = new DecimalFormat( "#,###,###,##0.00" );  
			System.out.println(stock + "\t" + df.format(price) + "\t" + df.format(offer) + "\t" + (up?"up":"down"));  
		} catch (JMSException e)
		{
			e.printStackTrace();
		}  
	}

}
