package com.ysd.springboot.service;

import com.ysd.springboot.entity.Statistics;

public interface StatisticsService {

	/**
	 * 通过阅览室id获取日志表中该阅览室的进入人数
	 * 
	 * @param reaid
	 * @return
	 */
	int getReadroomsCount(Integer reaid);
	/**
	 * 添加日记录表
	 * @param record
	 * @return
	 */
	int insert(Statistics record);

}
