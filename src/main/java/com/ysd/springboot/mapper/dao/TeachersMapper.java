package com.ysd.springboot.mapper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysd.springboot.entity.Teachers;

public interface TeachersMapper {
	
	//删除
    int deleteTeacherByPrimaryKey(Integer teaid);

    //添加
    int insertTeacher(Teachers record);

    Teachers selectTeacherByPrimaryKey(Integer teaid);

    //查询全部
    List<Teachers> selectAllTeacher(@Param("teaname")String teaname,@Param("tsex")String tsex);

    //修改
    int updateTeacherByPrimaryKey(Teachers record);
    
    int updateStatusByCardno(@Param("status")Integer status,@Param("cardno")String cardno);
}