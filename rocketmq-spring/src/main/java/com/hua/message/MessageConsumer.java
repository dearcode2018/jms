/**
  * @filename MessageConsumer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.message;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

 /**
 * @type MessageConsumer
 * @description 
 * @author qianye.zheng
 */
@Service
public class MessageConsumer extends CoreMessageService
{
	private String groupName = "consumer1";
	
	private DefaultMQPushConsumer defaultConsumer;
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void init()
	{

		defaultConsumer = new DefaultMQPushConsumer(groupName);
		defaultConsumer.setNamesrvAddr(nameServerAddr);
		defaultConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		
		// 订阅消息 , * 表示所有tag
		try
		{
			defaultConsumer.subscribe(topicName, "*");
			
			// 注册监听器，接收消息
			defaultConsumer.registerMessageListener(new MessageListenerConcurrently()
			{
				/**
				 * 
				 * @description 并行监听
				 * @param msgs
				 * @param context
				 * @return
				 * @author qianye.zheng
				 */
				@Override
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
						ConsumeConcurrentlyContext context)
				{
					System.out.println(Thread.currentThread().getName() + ": " + msgs);
					for (MessageExt e : msgs)
					{
						System.out.println("接收消息: " + new String(e.getBody()));
					}
					/*
					 * 返回状态
					 * CONSUME_SUCCESS 消费陈功
					 * RECONSUME_LATER 消费失败，待会重新消费
					 */
					
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});			
			// 启动消费者
			defaultConsumer.start();
			
			System.out.println("Consumer Started.");
		} catch (MQClientException e)
		{
			e.printStackTrace();
		}
	}
	
}
