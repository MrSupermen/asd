package com.ysd.springboot.service;

import java.util.List;

import com.ysd.springboot.entity.Students;

public interface StudentService {
		
	/**
	 * 删除
	 */
	public int deleteStudentById(Integer id);
	
	/**
	 * 添加
	 */
	public int insertStudent(Students stu);

	/**
	 * 修改学生信息
	 */
	public int updateStudentById(String name,String sex ,Integer id);
	
	/**
	 * 根据名字分页查询
	 */
	public List<Students> selectStudentByName(String name,Integer startIndex,Integer rows);
	
	/**
	 * 查询全学生信息
	 */
	public List<Students> selectAllStudent();
	
	public Students getStudentsById(Integer stuid);
	
	public int updateStatusByCardno(Integer status ,String cardno);
	

}
