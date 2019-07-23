package com.test.apiTest.baidu;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpUtils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.util.Base64ImageUtil;

/**
 * @author Hanley 
 * 2019年4月4日上午11:52:56
 *
 */
public class IdCardOCRTest {
		/**
	     * 重要提示代码中所需工具类
	     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
	     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
	     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
	     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
	     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
	     * 下载
	     */
	    public static void main(String[] args) {
	        // 身份证识别url
	        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
	        // 本地图片路径
	        //String filePath = "#####本地文件路径#####";
	        
			
	        try {
	        	File file = new File("D:\\work\\pic\\11.png");
				InputStream content = new FileInputStream(file);
				String base64 = Base64ImageUtil.GetImageStr(content);//io流图片转成base64文件
				
	            //byte[] imgData = FileUtil.readFileByBytes(filePath);
	            //String imgStr = Base64Util.encode(imgData);
	            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
	            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
	                    + URLEncoder.encode(base64, "UTF-8");
	            /**
	             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	             */
	            String accessToken = "24.76404c8987aae1a064029e4a5c0da9d4.2592000.1558064670.282335-16038972";
	            String result = HttpUtil.post(idcardIdentificate, accessToken, params);
	            System.out.println(result);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}
