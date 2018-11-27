package com.ysd.springboot.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ysd.springboot.entity.Readrooms;
import com.ysd.springboot.entity.Statistics;
import com.ysd.springboot.service.ReadroomsService;
import com.ysd.springboot.service.StatisticsService;

@Component
@EnableScheduling
public class Diary {
	@Autowired
	private StatisticsService staService;
	@Autowired
	private ReadroomsService reaService;
	
	//@Scheduled(cron="*/30 * * * * ?")
	public void addDiary() {
		System.out.println("添加日记录功能。。。。。。");
		Statistics statistics=new Statistics();
		List<Readrooms> reaList=reaService.selectAll();
		System.out.println("reaList==>"+reaList);
		for (Readrooms readrooms : reaList) {
			int reaNum=staService.getReadroomsCount(readrooms.getReaid());
			System.out.println("reaNum==>"+reaNum+";Reaid"+readrooms.getReaid());
			statistics.setReadroomid(readrooms.getReaid());
			statistics.setPeoplenums(reaNum);
			Calendar aCalendar = Calendar.getInstance();
			statistics.setYear(String.valueOf(aCalendar.get(Calendar.YEAR)));
			statistics.setMonth(String.valueOf(aCalendar.get(Calendar.MONTH) + 1));// 月份是从0开始的所以加1
			statistics.setDay(String.valueOf(aCalendar.get(Calendar.DATE)));
			System.out.println("statistics==>"+statistics);
			
			int n=staService.insert(statistics);
			System.out.println("添加日记录是否成功："+n);
		}
	}
	

}
