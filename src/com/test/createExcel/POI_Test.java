package com.test.createExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * @author Hanley 
 * 2018年11月15日下午3:09:13
 *
 */
public class POI_Test {

	public static void main(String[] args) {
        OutputStream out = null;
        try {
        	out = new FileOutputStream(new File("D:\\work\\test5.xls"));
            WriteExcel.write(out);
            System.out.print("文件生成成功");
             
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(out !=null){
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
