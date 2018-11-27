package com.ysd.springboot.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysd.springboot.entity.Readrooms;
import com.ysd.springboot.mapper.dao.ReadroomsMapper;
import com.ysd.springboot.service.ReadroomsService;
@Service
public class ReadroomsServiceImpl implements ReadroomsService{
	@Autowired
	private ReadroomsMapper mapper;

	@Override
	public List<Readrooms> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

}
