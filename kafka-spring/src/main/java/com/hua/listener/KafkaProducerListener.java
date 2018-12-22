/**
  * @filename KafkaProducerListener.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

 /**
 * @type KafkaProducerListener
 * @description 生产者监听器
 * @author qianye.zheng
 */
public class KafkaProducerListener implements ProducerListener<String, String>
{
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	/**
	 * @description 
	 * @param producerRecord
	 * @param exception
	 * @author qianye.zheng
	 */
	@Override
	public void onError(ProducerRecord<String, String> producerRecord, Exception exception)
	{
		ProducerListener.super.onError(producerRecord, exception);
	}

	/**
	 * @description 
	 * @param producerRecord
	 * @param recordMetadata
	 * @author qianye.zheng
	 */
	@Override
	public void onSuccess(ProducerRecord<String, String> producerRecord,
			RecordMetadata recordMetadata)
	{
		ProducerListener.super.onSuccess(producerRecord, recordMetadata);
	}

	/**
	 * @description 
	 * @param topic
	 * @param partition
	 * @param key
	 * @param value
	 * @param recordMetadata
	 * @author qianye.zheng
	 */
	@Override
	public void onSuccess(String topic, Integer partition, String key,
			String value, RecordMetadata recordMetadata)
	{
		ProducerListener.super.onSuccess(topic, partition, key, value, recordMetadata);
		log.info("Kafka生产者发送消息成功");
		log.info("topic: " + topic);
		log.info("key: " + key);
		log.info("value: " + value);
	}

	/**
	 * @description 
	 * @param topic
	 * @param partition
	 * @param key
	 * @param value
	 * @param exception
	 * @author qianye.zheng
	 */
	@Override
	public void onError(String topic, Integer partition, String key,
			String value, Exception exception)
	{
		ProducerListener.super.onError(topic, partition, key, value, exception);
		log.info("Kafka生产者发送消息失败");
		log.info("topic: " + topic);
		log.info("key: " + key);
		log.info("value: " + value);
	
	}

	
	
}
