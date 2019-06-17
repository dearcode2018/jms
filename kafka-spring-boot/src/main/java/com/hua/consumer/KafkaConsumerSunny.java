/**
  * @filename KafkaConsumerSunny.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.hua.entity.User;
import com.hua.util.JacksonUtil;

/**
 * @type KafkaConsumerSunny
 * @description 
 * @author qianye.zheng
 */
//@KafkaListener(topics = {"haha1"})
@Configuration
public class KafkaConsumerSunny
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
	
	
	/**
	 * 
	 * @description 
	 * 用到key和value，使用ConsumerRecord来声明
	 * @param recoder
	 * @author qianye.zheng
	 */
	@KafkaListener(topics = {"haha1"}, topicPartitions = {} /*, groupId = ""  可以定义消费者组，覆盖全局配置 */)
	public void processRecord(ConsumerRecord<String, User> recoder)
	{
		if (null != recoder.value())
		{
			System.out.println("1.receive message from kakfa server: " + recoder.key() + ", content = "  + recoder.value().getUsername());
		}
	}
	
	/**
	 * 
	 * @description 
	 * 没用到key，直接直接声明消息的bean，若消息为空则抛异常:
	 * Listener method could not be invoked with the incoming message
	 * @param user
	 * @author qianye.zheng
	 */
	@KafkaListener(topics = {"haha3"} /*, groupId = ""  可以定义消费者组，覆盖全局配置 */)
	public void process(User user)
	{
		if (null != user)
		{
			System.out.println("1.receive message from kakfa server: " + user.getUsername());
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @author qianye.zheng
	 */
	//@KafkaListener(topics = {"haha1"})
	public void process2(final String content)
	{
		System.out.println("2.receive message from kakfa server: " + content);
	}
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @author qianye.zheng
	 */
	//@KafkaListener(topics = {"haha1"})
	public void processWithObject(final User entity)
	{
		System.out.println("3.receive message from kakfa server: " + JacksonUtil.writeAsString(entity));
	}
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @author qianye.zheng
	 */
	//@KafkaListener(topics = {"haha2"})
	public void processN(final String content)
	{
		System.out.println("4.receive message from kakfa server: " + content);
	}
	
	
}
