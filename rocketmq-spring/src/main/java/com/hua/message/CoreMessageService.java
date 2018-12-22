/**
  * @filename CoreMessageService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.message;

import org.springframework.beans.factory.annotation.Value;

 /**
 * @type CoreMessageService
 * @description 
 * @author qianye.zheng
 */
public abstract class CoreMessageService
{
	@Value("${rocketmq.name.server.addr}")
	protected String nameServerAddr;
	
	@Value("${rocketmq.topic.name}")
	protected String topicName;
	
}
