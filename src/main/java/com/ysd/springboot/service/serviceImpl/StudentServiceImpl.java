package com.ysd.springboot.service.serviceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysd.springboot.entity.Students;
import com.ysd.springboot.mapper.dao.StudentsMapper;
import com.ysd.springboot.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentsMapper stuMapper;

	@Override
	public int deleteStudentById(Integer id) {
		// TODO Auto-generated method stub
		return stuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertStudent(Students stu) {
		// TODO Auto-generated method stub
		return stuMapper.insert(stu);
	}

	@Override
	public int updateStudentById(String name,String sex ,Integer id) {
		// TODO Auto-generated method stub
		return stuMapper.updateByPrimaryKey(name,sex,id);
	}

	@Override
	public List<Students> selectStudentByName(String name,Integer startIndex,Integer rows) {
		// TODO Auto-generated method stub
		return stuMapper.selectStudentByName(name,startIndex,rows);
	}

	@Override
	public List<Students> selectAllStudent() {
		// TODO Auto-generated method stub
		return stuMapper.selectAll();
	}

	@Override
	public Students getStudentsById(Integer stuid) {
		// TODO Auto-generated method stub
		return stuMapper.getStudentById(stuid);
	}

	@Override
	public int updateStatusByCardno(Integer status, String cardno) {
		// TODO Auto-generated method stub
		return stuMapper.updateStatusByCardno(status, cardno);
	}

}
