package com.ysd.springboot.mapper.dao;

import com.ysd.springboot.entity.Sections;
import java.util.List;

public interface SectionsMapper {

    int deleteByPrimaryKey(Integer secid);


    int insert(Sections record);


    Sections selectByPrimaryKey(Integer secid);


    List<Sections> selectAll();


    int updateByPrimaryKey(Sections record);
}