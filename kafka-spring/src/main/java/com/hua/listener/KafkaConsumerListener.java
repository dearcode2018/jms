/**
  * @filename KafkaConsumerListener.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

 /**
 * @type KafkaConsumerListener
 * @description 消费者监听器
 * @author qianye.zheng
 */
public class KafkaConsumerListener implements MessageListener<String, String>
{
	
	/*
	 * 消费者监听器，用来接收生产者发来的消息
	 * 
	 * 可以在这个监听器中实现对消息的处理逻
	 * 
	 * 
	 */
	
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	/**
	 * @description 
	 * @param data
	 * @author qianye.zheng
	 */
	@Override
	public void onMessage(ConsumerRecord<String, String> data)
	{
		log.info("Kafka消费者接收消息成功");
		log.info("topic: " + data.topic());
		log.info("key: " + data.key());
		log.info("value: " + data.value());
	}

}
