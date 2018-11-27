package com.ysd.springboot.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysd.springboot.entity.Statistics;
import com.ysd.springboot.mapper.dao.StatisticsMapper;
import com.ysd.springboot.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired
	private StatisticsMapper mapper;

	@Override
	public int getReadroomsCount(Integer reaid) {
		// TODO Auto-generated method stub
		return mapper.getReadroomsCount(reaid);
	}

	@Override
	public int insert(Statistics record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

}
