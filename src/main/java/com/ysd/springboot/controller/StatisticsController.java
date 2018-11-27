package com.ysd.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.springboot.entity.Readrooms;
import com.ysd.springboot.entity.Tubiao;
import com.ysd.springboot.service.ConsumelogsService;
import com.ysd.springboot.service.ReadroomsService;
import com.ysd.springboot.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private StatisticsService staService;
	@Autowired
	private ConsumelogsService conService;
	@Autowired
	private ReadroomsService reaService;

	@RequestMapping("/getPeopleByReaid")
	public Object getPeopleByReaid() {
		List<Tubiao> tubiaos = new ArrayList<Tubiao>();
		Map<String, Object> map = new HashMap<String, Object>();
		int sum = conService.selectAll().size();// 进入阅览室总人数
		List<Readrooms> reaList = reaService.selectAll();// 获取所有阅览室
		for (Readrooms readrooms : reaList) {
			int num = conService.getPeopleByReaid(readrooms.getReaid()).size();
			String reaName = readrooms.getReaname();
			Tubiao tubiao = new Tubiao(num, reaName);
			tubiaos.add(tubiao);
		}
		System.out.println("tubiaos==>" + tubiaos);
		System.out.println("sum==>" + sum);
		map.put("tubiaos", tubiaos);
		map.put("sum", sum);
		return map;
	}

}
