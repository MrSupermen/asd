package com.ysd.springboot.entity;

import java.io.Serializable;

public class Teachers implements Serializable {
    
    private Integer teaid;
    private String teacardno;
    private String teaname;
    private String teasex;
    private Integer sectionid;
    private Integer teastatus;
    private String tearemark;
    
    private static final long serialVersionUID = 1L;
    
    public Integer getTeaid() {
        return teaid;
    }
    public void setTeaid(Integer teaid) {
        this.teaid = teaid;
    }
    public String getTeacardno() {
        return teacardno;
    }
    public void setTeacardno(String teacardno) {
        this.teacardno = teacardno == null ? null : teacardno.trim();
    }
    public String getTeaname() {
        return teaname;
    }
    public void setTeaname(String teaname) {
        this.teaname = teaname == null ? null : teaname.trim();
    }
    public String getTeasex() {
        return teasex;
    }
    public void setTeasex(String teasex) {
        this.teasex = teasex == null ? null : teasex.trim();
    }
    public Integer getSectionid() {
        return sectionid;
    }
    public void setSectionid(Integer sectionid) {
        this.sectionid = sectionid;
    }
    public Integer getTeastatus() {
        return teastatus;
    }
    public void setTeastatus(Integer teastatus) {
        this.teastatus = teastatus;
    }
    public String getTearemark() {
        return tearemark;
    }
    public void setTearemark(String tearemark) {
        this.tearemark = tearemark == null ? null : tearemark.trim();
    }
	@Override
	public String toString() {
		return "Teachers [teaid=" + teaid + ", teacardno=" + teacardno + ", teaname=" + teaname + ", teasex=" + teasex
				+ ", sectionid=" + sectionid + ", teastatus=" + teastatus + ", tearemark=" + tearemark + "]";
	}
    
}