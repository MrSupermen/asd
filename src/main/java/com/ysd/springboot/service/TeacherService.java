package com.ysd.springboot.service;

import java.util.List;

import com.ysd.springboot.entity.Teachers;

public interface TeacherService {
	//删除
    int deleteTeacherByPrimaryKey(Integer teaid);

    //添加
    int insertTeacher(Teachers teacher);

    Teachers selectTeacherByPrimaryKey(Integer teaid);

    //查询全部
    List<Teachers> selectAllTeacher(String teaname,String tsex);

    //修改
    int updateTeacherByPrimaryKey(Teachers teacher);
    
    int updateStatusByCardno(Integer status ,String cardno);

}
