package com.ysd.springboot.controller;

import java.io.IOException;
import java.util.List;

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

import com.ysd.springboot.entity.Students;
import com.ysd.springboot.service.serviceImpl.StudentServiceImpl;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private StudentServiceImpl service;

	@RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
	public void downloadAllClassmate(HttpServletResponse response) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("信息表");
		List<Students> studentList = service.selectAllStudent();
		String fileName = "studentInfo" + ".xls";// 设置要导出的文件的名字 //新增数据行，并且设置单元格数据
		int rowNum = 1;
		String[] headers = { "学号", "姓名", "性別", "卡号" ,"备注"}; // headers表示excel表中第一行的表头
		HSSFRow row = sheet.createRow(0); // 在excel表中添加表头
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		} // 在表中存放查询到的数据放入对应的列
		for (Students Student : studentList) {
			HSSFRow row1 = sheet.createRow(rowNum);
			row1.createCell(0).setCellValue(Student.getStuno());
			row1.createCell(1).setCellValue(Student.getStuname());
			row1.createCell(2).setCellValue(Student.getStusex());
			row1.createCell(3).setCellValue(Student.getStucardno());
			row1.createCell(4).setCellValue(Student.getSturemark());
			rowNum++;
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.flushBuffer();
		workbook.write(response.getOutputStream());
	}
}
