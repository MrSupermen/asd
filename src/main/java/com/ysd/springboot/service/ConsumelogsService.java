package com.ysd.springboot.service;

import java.util.List;

import com.ysd.springboot.entity.Consumelogs;

public interface ConsumelogsService {
	
	int insertLog(Consumelogs consumelogs);
	
	Consumelogs selectByCardno(String cardno);
	
	int updateByCardno(Consumelogs consumelogs);
	
	List<Consumelogs> getPeopleByReaid(Integer reaid);
	
	List<Consumelogs> selectAll();
	

}
