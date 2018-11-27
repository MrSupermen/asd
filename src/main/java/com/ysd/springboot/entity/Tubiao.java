package com.ysd.springboot.entity;

//图标实体类
public class Tubiao {
	
	int peopleNum;//阅览室人数
	String reaName;//阅览室名称
	
	public Tubiao(int peopleNum, String reaName) {
		super();
		this.peopleNum = peopleNum;
		this.reaName = reaName;
	}
	
	public Tubiao() {
		super();
	}

	public int getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getReaName() {
		return reaName;
	}
	public void setReaName(String reaName) {
		this.reaName = reaName;
	}
	
	
	@Override
	public String toString() {
		return "Tubiao [peopleNum=" + peopleNum + ", reaName=" + reaName + "]";
	}
	
	

}
