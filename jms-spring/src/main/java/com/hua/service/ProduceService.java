/**
  * @filename ProduceService.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.service;

import javax.jms.Destination;

 /**
 * @type ProduceService
 * @description 生产服务
 * @author qye.zheng
 */
public interface ProduceService extends CoreService {

	/**
	 * 
	 * @description 
	 * @param destination
	 * @param message
	 * @author qye.zheng
	 */
	public void sendMessage(final Destination destination, final String message);
}
