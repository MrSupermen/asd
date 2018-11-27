package com.ysd.springboot.service.serviceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ysd.springboot.entity.Students;
import com.ysd.springboot.mapper.dao.StudentsMapper;
import com.ysd.springboot.service.ITestService;

@Service
public class TestServiceImpl implements ITestService{

	 @Autowired
	    private StudentsMapper Mapper;
	 
	 
	    @Transactional(readOnly = false,rollbackFor = Exception.class)
	    @Override
	    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
	 
	        boolean notNull = false;
	        List<Students> studentList = new ArrayList<Students>();
	        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
	            throw new Exception("上传文件格式不正确");
	        }
	        boolean isExcel2003 = true;
	        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
	            isExcel2003 = false;
	        }
	        InputStream is = file.getInputStream();
	        Workbook wb = null;
	        if (isExcel2003) {
	            wb = new HSSFWorkbook(is);
	        } else {
	            wb = new XSSFWorkbook(is);
	        }
	        Sheet sheet = wb.getSheetAt(0);
	        if(sheet!=null){
	            notNull = true;
	        }
	        Students student;
	        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
	            Row row = sheet.getRow(r);
	            if (row == null){
	                continue;
	            }
	 
	            student = new Students();
	            
	            /*if(!row.getCell(0).getCellType().equals(1)){
	                throw new Exception("导入失败(第"+(r+1)+"行,学号请设为文本格式)");
	            }*/
	            String stuno = row.getCell(0).getStringCellValue();
	 
	            /*if(stuno == null || stuno.isEmpty()){
	                throw new Exception("导入失败(第"+(r+1)+"行,学号未填写)");
	            }*/
	 
	            //row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
	            String stuname = row.getCell(1).getStringCellValue();
	            /*if(stuname==null || stuname.isEmpty()){
	                throw new Exception("导入失败(第"+(r+1)+"行,姓名未填写)");
	            }*/
	            
	            String stusex = row.getCell(2).getStringCellValue();
	            /*if(stusex==null){
	                throw new Exception("导入失败(第"+(r+1)+"行,性别未填写)");
	            }*/
	            
	            String stucardno = row.getCell(3).getStringCellValue();
	            /*if(stucardno==null){
	                throw new Exception("导入失败(第"+(r+1)+"行,不存在此卡号或未填写)");
	            }*/
	            
	            String sturemark = row.getCell(3).getStringCellValue();
	            /*if(sturemark==null){
	                throw new Exception("导入失败(第"+(r+1)+"行,不存在此备注或未填写)");
	            }*/
	 
	 
	            student.setStuno(stuno);
	            student.setStuname(stuname);
	            student.setStusex(stusex);
	            student.setStucardno(stucardno);
	            student.setSturemark(sturemark);	 
	            studentList.add(student);
	        }
	        for (Students studentResord : studentList) {
	                Mapper.insertExcel(studentResord);
	                System.out.println(" 插入 "+studentResord);
	        }
	        return notNull;
	    }

}
