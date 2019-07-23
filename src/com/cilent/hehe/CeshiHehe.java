package com.cilent.hehe;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.alibaba.fastjson.JSONObject;
import com.cilent.httpClientUtil;
import com.util.Base64ImageUtil;
import com.util.HttpUtil;

/**
 * @author Hanley 
 * 2019年3月13日上午11:20:43
 *
 */
public class CeshiHehe {
	
	public static void main(String[] args) throws Exception {
		
		String url ="https://api-intcloud.camcard.com/recognize/ocr/dongfeng";
		
		
		String user ="bibibaba";//dongfeng
		String api_key ="D223_12f9o+D223_12f9o";//ObIT5KtcMT7y
		String process ="2";
		String recognize_service ="fangkuanziliao";
		
		File file = new File("D:\\work\\pic\\diyadaikuanhetong.jpg");
		InputStream content = new FileInputStream(file);
		String base64 = Base64ImageUtil.GetImageStr(content);//io流图片转成base64文件
			
		
		
				
		Map<String,Object> parmerMap = new HashMap<String,Object>();
		parmerMap.putIfAbsent("user", user);
		parmerMap.putIfAbsent("api_key", api_key);
		parmerMap.putIfAbsent("process", process);
		parmerMap.putIfAbsent("recognize_service", recognize_service);
		parmerMap.putIfAbsent("image_data", base64);
		
		String reqStr = JSONObject.toJSONString(parmerMap);
		
		
		System.out.println(reqStr);
		
		HttpUtil hu = new HttpUtil();
		
		String returnStr = hu.doPost(url, reqStr);
	    
		System.out.println("url:"+url);
		System.out.println(" 得到数据 returnStr==" + returnStr);
	    
		
	}

}
