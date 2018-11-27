package com.ysd.springboot.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysd.springboot.entity.Sections;
import com.ysd.springboot.mapper.dao.SectionsMapper;
import com.ysd.springboot.service.SectionsService;

@Service
public class SectionsServiceimpl implements SectionsService{
	@Autowired
	private SectionsMapper mapper;

	@Override
	public Sections selectByPrimaryKey(Integer secid) {
		return mapper.selectByPrimaryKey(secid);
	}

}
