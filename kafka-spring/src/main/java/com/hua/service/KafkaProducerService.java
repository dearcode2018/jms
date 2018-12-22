/**
  * @filename KafkaProducerService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

 /**
 * @type KafkaProducerService
 * @description 
 * @author qianye.zheng
 */
@Service
public class KafkaProducerService
{
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
	/**
	 * 
	 * @description 
	 * @param topic
	 * @param key
	 * @param value
	 * @author qianye.zheng
	 */
	public void send(final String topic, final String key, final String value)
	{
		kafkaTemplate.send(topic, key, value);
	}
	
	/**
	 * 
	 * @description 
	 * @param topic
	 * @param data
	 * @author qianye.zheng
	 */
	public void send(final String topic, final String data)
	{
		kafkaTemplate.send(topic, data);
	}
	
}
