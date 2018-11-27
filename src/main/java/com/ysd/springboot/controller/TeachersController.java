package com.ysd.springboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.springboot.entity.Sections;
import com.ysd.springboot.entity.Students;
import com.ysd.springboot.entity.Teachers;
import com.ysd.springboot.service.SectionsService;
import com.ysd.springboot.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeachersController {
	@Autowired
	private TeacherService service;
	
	/**
	 * 查询所有教师
	 * @return
	 */
	
	@RequestMapping("/selectAllTeacher")
	public Object selectAllTeacher(String tname,String tsex) {
		Map<String,Object> map=new HashMap<String,Object>();
		List<Teachers> list=service.selectAllTeacher(tname,tsex);
		
		int total=list.size();
		System.out.println("list=>"+list+" ;total=>"+total);
		map.put("total", total);
		map.put("rows", list);
		System.out.println("map=>"+map);
		return map;
	}
	
	@RequestMapping("/insertTeacher")
	public Object insertTeacher(Teachers teacher) {
		Map<String,Object> map=new HashMap<String,Object>();
		int n=service.insertTeacher(teacher);
		if(n>0) {
			map.put("success", true);
			map.put("message", "添加成功");
		}else {
			map.put("success", false);
			map.put("message", "添加失败");
		}
		return map;
	}
	
	@RequestMapping("/deleteTeacher")
	public Object deleteTeacher(Integer teaid) {
		Map<String,Object> map=new HashMap<String,Object>();
		int n=service.deleteTeacherByPrimaryKey(teaid);
		if(n>0) {
			map.put("success", true);
			map.put("message", "删除成功");
		}else {
			map.put("success", true);
			map.put("message", "删除失败");
		}
		return map;
	}
	
	@RequestMapping("/updateTeacher")
	public Object updateTeacher(Teachers teacher) {
		Map<String,Object> map=new HashMap<String,Object>();
		int n=service.updateTeacherByPrimaryKey(teacher);
		if(n>0) {
			map.put("success", true);
			map.put("message", "修改成功");
		}else {
			map.put("success", true);
			map.put("message", "修改失败");
		}
		return map;
	}
	
	/**
	 * excel导出教师信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "TeacherExcelDownloads", method = RequestMethod.GET)
	public void downloadAllClassmate(HttpServletResponse response) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("教师信息表");
		String teaname=null;
		String tsex=null;
		List<Teachers> teacherList = service.selectAllTeacher(teaname, tsex);
		String fileName = "teacherInfo" + ".xls";// 设置要导出的文件的名字 //新增数据行，并且设置单元格数据
		int rowNum = 1;
		String[] headers = { "姓名", "性别", "卡号", "备注" ,"状态"}; // headers表示excel表中第一行的表头
		HSSFRow row = sheet.createRow(0); // 在excel表中添加表头
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		} // 在表中存放查询到的数据放入对应的列
		for (Teachers teacher : teacherList) {
			HSSFRow row1 = sheet.createRow(rowNum);
			row1.createCell(0).setCellValue(teacher.getTeaname());
			row1.createCell(1).setCellValue(teacher.getTeasex());
			row1.createCell(2).setCellValue(teacher.getTeacardno());
			row1.createCell(3).setCellValue(teacher.getTearemark());
			row1.createCell(4).setCellValue(teacher.getTeastatus());
			rowNum++;
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.flushBuffer();
		workbook.write(response.getOutputStream());
	}


}
