package com.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.controller.ReadExcel;
import com.dao.inter.ISuperDao;
import com.entity.User;

@Service
public class ImportService {
	 private static final Logger log = Logger.getLogger(ImportService.class);
		
		@Autowired
	    ISuperDao superDao;
	    
	    public String readExcelFile(MultipartFile file) {
	        String result = "";  
	        //创建处理EXCEL的类  
	        ReadExcel readExcel = new ReadExcel();  
	        //解析excel，获取上传的事件单  
	        List<Map<String, Object>> userList = readExcel.getExcelInfo(file);  
	        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,  
	        for(Map<String, Object> user:userList){
	            //int ret = userDao.insertUser(user.get("name").toString(), user.get("sex").toString(), Integer.parseInt(user.get("age").toString()));
	        	User u = new User();
	        	u.setName(user.get("name").toString());
	        	u.setSex(user.get("sex").toString());
	        	u.setAge(user.get("age").toString());
	        	Serializable id = superDao.addObject(u);
	        	if(id!=null){
	        		log.info("id:"+id+" 新增 成功！");
	        	}else{
	        		log.info("新增失败");
	        	}
	        }
	        if(userList != null && !userList.isEmpty()){  
	            result = "上传成功";  
	        }else{  
	            result = "上传失败";  
	        }  
	        return result;  
	    }

}
