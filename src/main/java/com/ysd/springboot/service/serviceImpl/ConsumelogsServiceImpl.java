package com.ysd.springboot.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysd.springboot.entity.Consumelogs;
import com.ysd.springboot.mapper.dao.ConsumelogsMapper;
import com.ysd.springboot.service.ConsumelogsService;
@Service
public class ConsumelogsServiceImpl implements ConsumelogsService{
	@Autowired
	private ConsumelogsMapper mapper;

	@Override
	public int insertLog(Consumelogs consumelogs) {
		// TODO Auto-generated method stub
		return mapper.insert(consumelogs);
	}

	@Override
	public Consumelogs selectByCardno(String cardno) {
		// TODO Auto-generated method stub
		return mapper.selectByCardno(cardno);
	}

	@Override
	public int updateByCardno(Consumelogs consumelogs) {
		// TODO Auto-generated method stub
		return mapper.updateByCardno(consumelogs);
	}

	@Override
	public List<Consumelogs> getPeopleByReaid(Integer reaid) {
		// TODO Auto-generated method stub
		return mapper.getPeopleByReaid(reaid);
	}

	@Override
	public List<Consumelogs> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

}
