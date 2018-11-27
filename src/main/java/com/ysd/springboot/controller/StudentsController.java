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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ysd.springboot.entity.Students;
import com.ysd.springboot.service.ITestService;
import com.ysd.springboot.service.serviceImpl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentsController {
	
	@Autowired
	private StudentServiceImpl service;
		
	
	/**
	 * 根据id删除学生
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteStudentById")
	public Object deleteStudentById(Integer id) {
		Map<String , Object> map = new HashMap<String , Object>();
		int n = service.deleteStudentById(id);
		if(n>0) {
			map.put("success",true);
			map.put("message", "删除成功");
		}else {
			map.put("success",false);
			map.put("message", "删除失败");
		}
		return map;
	}
	/**
	 * 添加学生
	 * @param stu
	 * @return
	 */
	@RequestMapping("/insertStudent")
	public Object insertStudent(String name,String sex,String stuCardno,String stuNO,String remark,Integer membershipID) {
		Students stu=new Students();
		stu.setMembershipid(membershipID);
		stu.setStucardno(stuCardno);
		stu.setStuname(name);
		stu.setStuno(stuNO);
		stu.setStusex(sex);
		stu.setSturemark(remark);
		System.out.println("stu=>"+stu);
		Map<String , Object> map = new HashMap<String , Object>();
		int n=service.insertStudent(stu);
		if(n>0) {
			map.put("success",true);
			map.put("message", "添加成功");
		}else {
			map.put("success",false);
			map.put("message", "添加失败");
		}
		return map;		
	}
	/**
	 * 修改学生
	 * @param stu
	 * @return
	 */
	@RequestMapping("/updateStudent")
	public Object updateStudent(String name,String sex,Integer stuid) {
		Map<String , Object> map = new HashMap<String , Object>();
		int n=service.updateStudentById(name,sex,stuid);
		if(n>0) {
			map.put("success",true);
			map.put("message", "修改成功");
		}else {
			map.put("success",false);
			map.put("message", "修改失败");
		}
		return map;
	}
	/**
	 * 查询所有学生
	 * @return
	 */
	@RequestMapping("/selectAllStudent")
	public Object selectAllStudent(Integer page,Integer rows) {
		Map<String , Object> map = new HashMap<String , Object>();
		List<Students> list=service.selectAllStudent();
		int total=list.size();
		System.out.println("list=>"+list+" ;total=>"+total);
		map.put("total", total);
		map.put("rows", list);
		System.out.println("map=>"+map);
		return map;
		
	}
	/**
	 * 条件查询
	 * @param name
	 * @return
	 */
	@RequestMapping("/selectStudentByName")
	public Object selectStudentByName(String name,Integer page,Integer rows) {
		System.out.println("name=>"+name+";startindex=>"+(page-1)*rows+";rows=>"+rows);
		Map<String , Object> map = new HashMap<String , Object>();
		List<Students> list=service.selectStudentByName(name,(page-1)*rows,rows);
		List<Students> list2=service.selectAllStudent();
		int total=list2.size();
		System.out.println("list=>"+list+" ;total=>"+total);
		map.put("total", total);
		map.put("rows", list);
		System.out.println("map=>"+map);
		return map;
						
	}
	/**
	 * excel到出学生信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
	public void downloadAllClassmate(HttpServletResponse response) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("学生信息表");
		List<Students> studentList = service.selectAllStudent();
		String fileName = "studentInfo" + ".xls";// 设置要导出的文件的名字 //新增数据行，并且设置单元格数据
		int rowNum = 1;
		String[] headers = { "学号", "姓名", "性別", "卡号" ,"备注","院系"}; // headers表示excel表中第一行的表头
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
			row1.createCell(5).setCellValue(Student.getMembershipid());
			rowNum++;
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.flushBuffer();
		workbook.write(response.getOutputStream());
	}
	
	/**
	 * excel导入
	 */
	@Autowired
    private ITestService testService;
 
    @RequestMapping("/importExcel")
    public String addUser(@RequestParam("file")MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
             a = testService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(a) {
        	return "上传成功";
        }else {
        	return "上传失败";
        }
    }

}
