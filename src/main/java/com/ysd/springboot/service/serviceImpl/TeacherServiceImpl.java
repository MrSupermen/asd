package com.ysd.springboot.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysd.springboot.entity.Teachers;
import com.ysd.springboot.mapper.dao.TeachersMapper;
import com.ysd.springboot.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeachersMapper mapper;

	@Override
	public int deleteTeacherByPrimaryKey(Integer teaid) {
		return mapper.deleteTeacherByPrimaryKey(teaid);
	}

	@Override
	public int insertTeacher(Teachers teacher) {
		// TODO Auto-generated method stub
		return mapper.insertTeacher(teacher);
	}

	@Override
	public Teachers selectTeacherByPrimaryKey(Integer teaid) {
		// TODO Auto-generated method stub
		return mapper.selectTeacherByPrimaryKey(teaid);
	}

	@Override
	public List<Teachers> selectAllTeacher(String teaname,String tsex) {
		// TODO Auto-generated method stub
		return mapper.selectAllTeacher(teaname,tsex);
	}

	@Override
	public int updateTeacherByPrimaryKey(Teachers teacher) {
		// TODO Auto-generated method stub
		return mapper.updateTeacherByPrimaryKey(teacher);
	}

	@Override
	public int updateStatusByCardno(Integer status, String cardno) {
		// TODO Auto-generated method stub
		return mapper.updateStatusByCardno(status, cardno);
	}

}
