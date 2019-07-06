/**
 * 描述: 
 * ConsumerAPITest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.boot;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import com.hua.ApplicationStarter;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ConsumerAPITest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
// for Junit 5.x
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration(value = "src/main/webapp")
@SpringBootTest(classes = {ApplicationStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@MapperScan(basePackages = {"com.hua.mapper"})
public final class ConsumerAPITest extends BaseTest {

	
	/*
	配置方式1: 
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextConfiguration(locations = {
			"classpath:conf/xml/spring-bean.xml", 
			"classpath:conf/xml/spring-config.xml", 
			"classpath:conf/xml/spring-mvc.xml", 
			"classpath:conf/xml/spring-service.xml"
		})
	@ExtendWith(SpringExtension.class)
	
	配置方式2: 	
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextHierarchy({  
		 @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),  
		 @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
		}) 
	@ExtendWith(SpringExtension.class)
	 */
	
	/**
	 * 而启动spring 及其mvc环境，然后通过注入方式，可以走完 spring mvc 完整的流程.
	 * 
	 */
	//@Resource
	//private UserController userController;
	/*
	 * key: String，序列化: org.apache.kafka.common.serialization.StringSerializer， 反序列化: org.apache.kafka.common.serialization.StringDeserializer
	 * value:  Object, 序列化: org.springframework.kafka.support.serializer.JsonSerializer，反序列化: org.springframework.kafka.support.serializer.JsonDeserializer
	 * 
	 * 注意: 反序列化除了要指定反序列化器，还要配置信任包路径.spring.kafka.consumer.properties[spring.json.trusted.packages]
	 * 
	 * 
	 */
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.consumer.key-deserializer}")
	private String keyDeserializer;
	
	@Value("${spring.kafka.consumer.value-deserializer}")
	private String valueDeserializer;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@Resource
	private KafkaTemplate<String, Object> kafkaTemplate;	
	/**
	 * 引当前项目用其他项目之后，然后可以使用
	 * SpringJunitTest模板测试的其他项目
	 * 
	 * 可以使用所引用目标项目的所有资源
	 * 若引用的项目的配置与本地的冲突或无法生效，需要
	 * 将目标项目的配置复制到当前项目同一路径下
	 * 
	 */
	
	
	/**
	 * partition/offset的值都是从0开始，KafaManger上显示的有多少个partition.
	 * 
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSeekIndex() {
		try {
			 Properties props = new Properties();
	        props.put("bootstrap.servers", bootstrapServers);
	        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("group.id", groupId);			
	        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);	
	        String topic = null;
	        topic = "haha1";
	        topic = "friday";
	        /*
	         * 从某个分区开始，有多个分区则需要循环变量从多个分区获取数据
	         */
	        TopicPartition seekToEndPartition = new TopicPartition(topic, 0);
	        
	        consumer.assign(Arrays.asList(seekToEndPartition));
	        /*
	         * 从指定位置开始消费
	         * 若该位置没有，则会等待 直至超时
	         * 尚不明确为什么有些位置没有消息
	         */
	        consumer.seek(seekToEndPartition, 13);
	        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(1));
	        for (ConsumerRecord<String, String> record : records)
	        {
	        	System.out.println("partition = " + record.partition() + ", offset = " 
	        + record.offset() + ", " + ", key = " + record.key() + ", value = " + record.value());
	        }
	        
	        consumer.close();
		} catch (Exception e) {
			log.error("testSeekIndex =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSeekToBeginning() {
		try {
			 Properties props = new Properties();
	        props.put("bootstrap.servers", bootstrapServers);
	        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("group.id", groupId);			
	        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);	
	        String topic = null;
	        topic = "haha1";
	        topic = "friday";
	        /*
	         * 从某个分区开始，有多个分区则需要循环变量从多个分区获取数据
	         */
	        TopicPartition seekToEndPartition = new TopicPartition(topic, 0);
	        
	        consumer.assign(Arrays.asList(seekToEndPartition));
	        /*
	         * 从头开始搜索
	         * 从最开头开始，消费到最后，从拉取时刻算起，拉取完成之前接入的消息
	         * 会不断被拉取下来，直至超时
	         */
	        consumer.seekToBeginning(Arrays.asList(seekToEndPartition));
	        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(1));
	        // 时间太短 就超时，拉取失败
	        //ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(20));
	        for (ConsumerRecord<String, String> record : records)
	        {
	        	System.out.println("partition = " + record.partition() + ", offset = " 
	        + record.offset() + ", " + ", key = " + record.key() + ", value = " + record.value());
	        }
	        
	        consumer.close();
		} catch (Exception e) {
			log.error("testSeekToBeginning =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSeekToEnd() {
		try {
			 Properties props = new Properties();
	        props.put("bootstrap.servers", bootstrapServers);
	        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("group.id", groupId);			
	        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);	
	        String topic = null;
	        topic = "haha1";
	        topic = "friday";
	        /*
	         * 从某个分区开始，有多个分区则需要循环变量从多个分区获取数据
	         */
	        TopicPartition seekToEndPartition = new TopicPartition(topic, 0);
	        
	        consumer.assign(Arrays.asList(seekToEndPartition));
	        // 从结尾开始搜索
	        consumer.seekToEnd(Arrays.asList(seekToEndPartition));
	        //consumer.seek(seekToEndPartition, 0);
	        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(1));
	        for (ConsumerRecord<String, String> record : records)
	        {
	        	System.out.println("partition = " + record.partition() + ", offset = " 
	        + record.offset() + ", " + ", key = " + record.key() + ", value = " + record.value());
	        }
	        
	        consumer.close();
		} catch (Exception e) {
			log.error("testSeekToEnd =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSeekMutiplePartition() {
		try {
			 Properties props = new Properties();
	        props.put("bootstrap.servers", bootstrapServers);
	        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("group.id", groupId);			
	        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);	
	        String topic = null;
	        topic = "haha1";
	        /*
	         * 从某个分区开始，有多个分区则需要循环变量从多个分区获取数据
	         */
	        for (int i =0; i < 4; i++)
	        {
	        	TopicPartition seekToEndPartition = new TopicPartition(topic, i);
	        	
	        	consumer.assign(Arrays.asList(seekToEndPartition));
	        	/*
	        	 * 从头开始搜索
	        	 * 从最开头开始，消费到最后，从拉取时刻算起，拉取完成之前接入的消息
	        	 * 会不断被拉取下来，直至超时
	        	 */
	        	consumer.seekToBeginning(Arrays.asList(seekToEndPartition));
	        	ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
	        	// 时间太短 就超时，拉取失败
	        	//ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(20));
	        	for (ConsumerRecord<String, String> record : records)
	        	{
	        		System.out.println("partition = " + record.partition() + ", offset = " 
	        				+ record.offset() + ", " + ", key = " + record.key() + ", value = " + record.value());
	        	}
	        	
	        }
	        
	        consumer.close();
		} catch (Exception e) {
			log.error("testSeekToBeginning =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testValue() {
		try {
			
			
		} catch (Exception e) {
			log.error("testValue =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
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
	@DisplayName("testTemp")
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
	@DisplayName("testCommon")
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
	@DisplayName("testSimple")
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
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
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
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
	}

}
