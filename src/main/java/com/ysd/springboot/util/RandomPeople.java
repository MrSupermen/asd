package com.ysd.springboot.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ysd.springboot.entity.Consumelogs;
import com.ysd.springboot.entity.Students;
import com.ysd.springboot.entity.Teachers;
import com.ysd.springboot.service.ConsumelogsService;
import com.ysd.springboot.service.ReadroomsService;
import com.ysd.springboot.service.StudentService;
import com.ysd.springboot.service.TeacherService;

@Component
@EnableScheduling
public class RandomPeople{
	@Autowired
	private ReadroomsService reaService;
	@Autowired
	private StudentService stuService;
	@Autowired
	private TeacherService teaService;
	@Autowired
	private ConsumelogsService conService;

	//@Scheduled(cron="*/5 * * * * ?")
	public void random() {
		// TODO Auto-generated method stub
		//随机一个阅览室
		int reaNum=reaService.selectAll().size();
		int reaRandom=(int)(Math.random()*reaNum)+1;
		
		//随机一个学生或老师
		List<Students> stuList=stuService.selectAllStudent();
		int stuNum=stuList.size();
		int stuRandom=(int)(Math.random()*stuNum)+1;
		
		List<Teachers> teaList=teaService.selectAllTeacher(null, null);
		int teaNum=teaList.size();
		int teaRandom=(int)(Math.random()*teaNum)+1;
		
		System.out.println("reaRandom==>"+reaRandom+";stuRandom==>"+stuRandom+";teaRandom==>"+teaRandom);
		
		if(stuRandom>teaRandom) {
			Students student=stuService.getStudentsById(stuRandom);
			String stucardno=student.getStucardno();
			Integer stustatus=student.getStustatus();
			
			if(conService.selectByCardno(stucardno)==null) {
				Consumelogs con=new Consumelogs();
				con.setCardno(stucardno);
				con.setReadroomid(reaRandom);
				con.setIntime(new Date());
				con.setStatus(1);
				stuService.updateStatusByCardno(reaRandom, stucardno);
				
				int n=conService.insertLog(con);
				System.out.println("consumelogs插入学生日志状态："+n);
			}else {
				Consumelogs con=new Consumelogs();
				if(stustatus==0) {
					con.setCardno(stucardno);
					con.setIntime(new Date());
					con.setStatus(1);
					int n=conService.updateByCardno(con);
					System.out.println("consumelogs状态为0的学生日志更新状态："+n);
				}else {
					con.setCardno(stucardno);
					con.setOuttime(new Date());
					con.setStatus(0);
					int n=conService.updateByCardno(con);
					System.out.println("consumelogs状态>0的学生日志更新状态："+n);
				}
			}
		}else {
			Teachers teachers=teaService.selectTeacherByPrimaryKey(teaRandom);
			
			String teacardno=teachers.getTeacardno();
			Integer teastatus=teachers.getTeastatus();
			
			if(conService.selectByCardno(teacardno)==null) {
				Consumelogs con=new Consumelogs();
				con.setCardno(teacardno);
				con.setReadroomid(reaRandom);
				con.setIntime(new Date());
				con.setStatus(1);
				teaService.updateStatusByCardno(reaRandom, teacardno);
				
				int n=conService.insertLog(con);
				System.out.println("consumelogs插入教师日志状态："+n);
			}else {
				Consumelogs con=new Consumelogs();
				if(teastatus==0) {
					con.setCardno(teacardno);
					con.setIntime(new Date());
					con.setStatus(1);
					int n=conService.updateByCardno(con);
					System.out.println("consumelogs状态为0的教师日志更新状态："+n);
				}else {
					con.setCardno(teacardno);
					con.setOuttime(new Date());
					con.setStatus(0);
					int n=conService.updateByCardno(con);
					System.out.println("consumelogs状态>0的教师日志更新状态："+n);
				}
			}
		}
	}
	

}
