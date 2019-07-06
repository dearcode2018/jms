/**
  * @filename WriteService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hua.entity.CollegeStudent;
import com.hua.mapper.auto.CollegeStudentMapper;

/**
 * @type WriteService
 * @description 写服务，需事务支持
 * @author qianye.zheng
 */
@Service
public class WriteService
{

	@Resource
	private CollegeStudentMapper collegeStudentMapper;
	
	@Resource
	private DataSourceTransactionManager transactionManager;
	
	/**
	 * 
	 * @description 
	 * 数据源: 不标注数据源，使用默认的数据源
	 * @author qianye.zheng
	 */
	@Transactional
	public void insert(final CollegeStudent entity)
	{
		try
		{
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		/*
		 * 保存一个实体，null的属性不会保存，会使用数据库默认值
		 *  通常情况下，insertSelectvie使用得较多
		 */
		collegeStudentMapper.insertSelective(entity);
	}

}
