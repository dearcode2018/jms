/**
 * 描述: 
 * KafkaTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.kafka;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * KafkaTest
 */
public final class KafkaTest extends BaseTest {

	private static final String BOOTSTRAP_SERVERS = "127.0.0.1:9092";
	
	private static final String TOPIC_NAME = "test_topic";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testKafka() {
		try {
			
			
		} catch (Exception e) {
			log.error("testKafka =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCreateTopic() {
		try {
			Properties props = new Properties();
			props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
			// org.apache.kafka.clients.admin.AdminClient
			AdminClient adminClient = AdminClient.create(props);
			List<NewTopic> topics = new ArrayList<NewTopic>();
			NewTopic topic = null;
			// name, partitions, replication-factor
			topic = new NewTopic(TOPIC_NAME, 1, (short)1);
			topics.add(topic);
			CreateTopicsResult result = adminClient.createTopics(topics);
			
			result.all().get();
		} catch (Exception e) {
			log.error("testCreateTopic =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDeleteTopic() {
		try {
			Properties props = new Properties();
			props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
			// org.apache.kafka.clients.admin.AdminClient
			AdminClient adminClient = AdminClient.create(props);
			List<String> topics = new ArrayList<String>();
			topics.add(TOPIC_NAME);
			DeleteTopicsResult result = adminClient.deleteTopics(topics);
			
			//result.all().get();
			
		} catch (Exception e) {
			log.error("testDeleteTopic =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testProducerClient() {
		try {
			Properties props = new Properties();
			props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
			props.put("acks", "all");
			props.put("retries", "0");
			props.put("batch.size", 1638);
			props.put("linger.ms", 1);
			props.put("buffer.memory", 33554432);
			props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			// 创建生产者，org.apache.kafka.clients.producer
			Producer<String, String> producer = new KafkaProducer<String, String>(props);
			String key = "myKey2";
			String value = "myValue2";
			ProducerRecord<String, String> producerRecord =
					new ProducerRecord<String, String>(TOPIC_NAME, key, value);
			// 发送消息
			producer.send(producerRecord);
			
			// 关闭
			producer.close();
			
		} catch (Exception e) {
			log.error("testProducerClient =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConsumerClient() {
		try {
			Properties props = new Properties();
			// bootstrap.servers
			props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
			// 分组id
			props.put("group.id", "group_001");
			props.put("enable.auto.commit", true);
			// 自动提交时间间隔
			props.put("auto.commit.interval.ms", 1000);
			props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			
			// 创建消费者
			Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
			// 订阅消息
			List<String> topics = new ArrayList<String>();
			topics.add(TOPIC_NAME);
			consumer.subscribe(topics, new ConsumerRebalanceListener() {
				/**
				 * @description 分区已分配
				 * @param partitions
				 * @author qianye.zheng
				 */
				@Override
				public void onPartitionsAssigned(
						Collection<TopicPartition> partitions)
				{
					// 将偏移设置到最开始
					consumer.seekToBeginning(partitions);
				}
				
				/**
				 * @description 分区已撤销
				 * @param partitions
				 * @author qianye.zheng
				 */
				@Override
				public void onPartitionsRevoked(
						Collection<TopicPartition> partitions)
				{
				}
			});
			
			// 接收消息
			while (true)
			{
				ConsumerRecords<String, String> records = consumer.poll(1000L);
				for (ConsumerRecord<String, String> record : records)
				{
					System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), 
							record.key(), record.value());
				}
			}
			
		} catch (Exception e) {
			log.error("testConsumerClient =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
