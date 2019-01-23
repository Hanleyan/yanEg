package com.test.createExcel;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.entity.User;

/**
 * @author Hanley 
 * 2018年11月15日下午3:07:41
 *
 */
public class WriteExcel {
	
	public static void write(OutputStream outputStream) throws SQLException{
        //初始一个workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        List<User> usersList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
        	User u = new User();
        	u.setId(i);
        	u.setAge(i*10+1+"");
        	u.setName("名字"+1);
        	u.setSex("男");
			usersList.add(u);
		}
        
        //创建单个sheet
        HSSFSheet sheet = workbook.createSheet("用户");
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,3));//添加合并单元格，第一个参数是起始行，第二个参数是终止行，第三个参数是起始列，第四个参数是终止列
        sheet.addMergedRegion(new CellRangeAddress(2,2,0,3));//添加合并单元格，第一个参数是起始行，第二个参数是终止行，第三个参数是起始列，第四个参数是终止列
        
        /******************************设置表头*****************************/
        //创建多行
        //创建第二行
        HSSFRow row0 = sheet.createRow(1);
        row0.setHeightInPoints((short) 37); //设置行高
        
        //-----------------------------------------------
     // 第三步创建标题的单元格样式style2以及字体样式headerFont1
     			HSSFCellStyle style2 = workbook.createCellStyle();
     			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
     			style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
     			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     			HSSFFont headerFont1 = (HSSFFont) workbook.createFont(); // 创建字体样式
     			headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
     			headerFont1.setFontName("黑体"); // 设置字体类型
     			headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
     			style2.setFont(headerFont1); // 为标题样式设置字体样式
     			HSSFCell cell1 = row0.createCell(0);// 创建标题第一列
    			
    			//cell1.setCellValue(titleName); // 设置值标题
    			cell1.setCellStyle(style2); // 设置标题样式

 

        //-----------------------------------------------

        
        for(int cellIndex = 0;cellIndex < 1;cellIndex++){
            HSSFCell cell = row0.createCell(cellIndex);
            switch(cellIndex){
            case 0:
                cell.setCellValue("用户名称:interface_user.name");
                break;
            }
        } 
      //创建第三行 
        HSSFRow row1 = sheet.createRow(2);
        row1.setHeight((short) 600); 
        for(int cellIndex = 0;cellIndex < 1;cellIndex++){
            HSSFCell cell = row1.createCell(cellIndex);
            switch(cellIndex){
            case 0:
                cell.setCellValue("徵万企业查询对账单");
                break;
            }
        } 
      //创建第四行，设置列名
        HSSFRow row2 = sheet.createRow(3);
        row2.setHeight((short) 600); 
        for(int cellIndex = 0;cellIndex < 4;cellIndex++){
            HSSFCell cell = row2.createCell(cellIndex);
            switch(cellIndex){
            case 0:
                cell.setCellValue("ID");
                break;
            case 1:
                cell.setCellValue("姓名");
                break;
            case 2:
                cell.setCellValue("年龄");
                break;
            case 3:
                cell.setCellValue("性别");
                break;    
            }
        } 
        
        /**********************遍历物料，并将物料列表显示到模板中，作为邮件的附件***********************/
        //创建剩余行
        //for(int rowIndex = 4;rowIndex <= usersList.size();rowIndex++){
        for(int i = 0;i < usersList.size();i++){
            HSSFRow row = sheet.createRow(i+4);//从第5行开始
            User user = usersList.get(i);
            //创建多列
            for(int cellIndex = 0;cellIndex < 4;cellIndex++){
                HSSFCell cell = row.createCell(cellIndex);
                switch(cellIndex){
                case 0:
                    cell.setCellValue(user.getId());
                    break;
                case 1:
                    cell.setCellValue(user.getAge());
                    break;
                case 2:
                    cell.setCellValue(user.getName());
                    break;
                case 3:
                    cell.setCellValue(user.getSex());
                    break;
            
                }
            } 
        }
        
        //创建第2个sheet
        HSSFSheet sheet1 = workbook.createSheet("企业");
      //创建多行
        //创建第二行
        HSSFRow sheet1row0 = sheet1.createRow(1);
        for(int cellIndex = 0;cellIndex < 1;cellIndex++){
            HSSFCell cell = sheet1row0.createCell(cellIndex);
            switch(cellIndex){
            case 0:
                cell.setCellValue("企业名称:interface_supplier.supplier_name");
                break;
            }
        } 
      //创建第三行 
        HSSFRow sheet1row1 = sheet1.createRow(2);
        for(int cellIndex = 0;cellIndex < 1;cellIndex++){
            HSSFCell cell = sheet1row1.createCell(cellIndex);
            switch(cellIndex){
            case 0:
                cell.setCellValue("徵万企业查询对账单");
                break;
            }
        } 
      //创建第四行，设置列名
        HSSFRow sheet1row2 = sheet1.createRow(3);
        for(int cellIndex = 0;cellIndex < 4;cellIndex++){
            HSSFCell cell = sheet1row2.createCell(cellIndex);
            switch(cellIndex){
            case 0:
                cell.setCellValue("ID");
                break;
            case 1:
                cell.setCellValue("姓名");
                break;
            case 2:
                cell.setCellValue("年龄");
                break;
            case 3:
                cell.setCellValue("性别");
                break;    
            }
        } 
        //创建剩余行
        //for(int rowIndex = 4;rowIndex <= usersList.size();rowIndex++){
        for(int i = 0;i < usersList.size();i++){
            HSSFRow row = sheet1.createRow(i+4);//从第5行开始
            User user = usersList.get(i);
            //创建多列
            for(int cellIndex = 0;cellIndex < 4;cellIndex++){
                HSSFCell cell = row.createCell(cellIndex);
                switch(cellIndex){
                case 0:
                    cell.setCellValue(user.getId());
                    break;
                case 1:
                    cell.setCellValue(user.getAge());
                    break;
                case 2:
                    cell.setCellValue(user.getName());
                    break;
                case 3:
                    cell.setCellValue(user.getSex());
                    break;
            
                }
            } 
        }
        
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
