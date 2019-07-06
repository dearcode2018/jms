/**
  * @filename SimulateConsumer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.consumer;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.hua.constant.TopicConstant;
import com.hua.entity.CollegeStudent;
import com.hua.service.WriteService;

/**
 * @type SimulateConsumer
 * @description 
 * @author qianye.zheng
 */
//@KafkaListener(topics = {"haha1"})
@Configuration
public class SimulateConsumer
{
	
	/**
	 * 一个@KafkaListener 绑定一个主题
	 * 
	 * 多个方法绑定一个主题，相当于多个实例，一个组或多个组.
	 * 
	 * 生产者 随机分配分区，而每个消费者托管的分区是不同的.
	 * 
	 * 
	 * key/value序列化、反序列化
	 * 
	 * ConsumerRecord: 同时需要key和value
	 * 自定义Bean: 只需要value
	 * 
	 */
	@Resource
	private WriteService writeService;
	
	
	/*
	 * 一个主题多个消费者实例，客户端前缀使用同一个值即可
	 * 以和其他主题的消费者实例区分开
	 */
	private static final String CLIENT_ID_PREFIX = "somePrefix";
	
	/**
	 * 
	 * @description 
	 * 用到key和value，使用ConsumerRecord来声明
	 * @param recoder
	 * @author qianye.zheng
	 */
	@KafkaListener(topics = {TopicConstant.TOPIC_SIMULATE}, clientIdPrefix = CLIENT_ID_PREFIX)
	public void consumer1(CollegeStudent entity)
	{
		System.out.println("SimulateConsumer.consumer1()");
		writeService.insert(entity);
	}

	/**
	 * 
	 * @description 
	 * 用到key和value，使用ConsumerRecord来声明
	 * @param recoder
	 * @author qianye.zheng
	 */
	@KafkaListener(topics = {TopicConstant.TOPIC_SIMULATE}, clientIdPrefix = CLIENT_ID_PREFIX)
	public void consumer2(CollegeStudent entity)
	{
		System.out.println("SimulateConsumer.consumer2()");
		writeService.insert(entity);
	}
	
	/**
	 * 
	 * @description 
	 * 用到key和value，使用ConsumerRecord来声明
	 * @param recoder
	 * @author qianye.zheng
	 */
	@KafkaListener(topics = {TopicConstant.TOPIC_SIMULATE}, clientIdPrefix = CLIENT_ID_PREFIX)
	public void consumer3(CollegeStudent entity)
	{
		System.out.println("SimulateConsumer.consumer3()");
		writeService.insert(entity);
	}
	
}
