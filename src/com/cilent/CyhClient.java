package com.cilent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.util.HttpUtil;
import com.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Alan
 */

public class CyhClient {
        //private static  String queryDetaliByLicenseNo = "/cxy/driverlicenseservice/query";
       
       

        private static Logger log=Logger.getLogger(CyhClient.class);
        //请求地址
        static String url = "";
        static String violationUrl = "";
       
        static String appid = "shsaxtest";  //由车行易提供   yourappid
        
        static String secretKey  = "261917D00A8B4CD080EF0A5C7E244E90";  //由车行易提供
        
        // eg:http://test.cx580.com:8311/cxy/xxx/query?appid=baidunuomitest&timespan=1520925969&sign=84B9C3095958C622E5A1A0645042B218
     /**
      * sign算法：
			将接口输入参数所有字段的值按字典默认排序，按排序顺序将所有参数值拼接为字符串，最后将时间戳+参数拼接字符串+密钥的结果进行MD5加密，MD5值需为大写。
			如：
			secretKey = 8D9961195E104A729ADE3B4A4407C2CA
			timespan =1520925969
			postData={"carNumber":"粤A88888","carType":"02"}
			
			则：输入参数值排序并拼接字符串结果为
			paramsStr = 02粤A88888
			
			signStr = timespan + paramsStr + secretKey 
			signStr = 152092596902粤A888888D9961195E104A729ADE3B4A4407C2CA
			
			sign= MD5(signStr) 
			sign =04DA0C5A02B7152ED2E502297DA76A08
      */
         
        static{
            Properties pro = new Properties();

            InputStream in = null;
            try {
                in=CyhClient.class.getClassLoader().getResourceAsStream("/cyh.properties");
                pro.load(in);
                in.close();
                url = pro.getProperty("cyh.driverlicense.url");//驾驶证信息url
                violationUrl = pro.getProperty("cyh.violation.url");//违章查询url
                if(StringUtils.isBlank(url))
                    throw new RuntimeException("cyh.properties 属性读取不到");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

	    /**
	     * 驾驶证数据
	     * 查询驾驶证详细信息
	     * @param channelUserId
	     * @param phoneNum
	     * @return
	     */
        public  static String getDetaliByLicenseNo(String licenseNo){
        	//String reqStr = "licenseNo:"+licenseNo;//请求参数
        	
        	Date date = new Date();
        	long l = date.getTime();
        	String timespan = l + ""; //时间戳
        	
        	String resUrl = "/cxy/driverlicenseservice/query";
        	
        	String[] str={licenseNo};
            String paramsStr = getParamsStr(str);//输入参数所有字段的值按字典默认排序，按排序顺序将所有参数值拼接为字符串
            
            String signStr = timespan + paramsStr + secretKey ;
            log.info("加密前signStr:"+signStr);
            signStr= MD5Util.MD5(signStr);//时间戳+参数拼接字符串+密钥的结果进行MD5加密，MD5值需为大写
            
            //JSONObject jsonObject = new JSONObject();
            Map<String, Object> ingredients = new HashMap<String, Object>();
            ingredients.put("licenseNo", licenseNo);
            /*jsonObject.putAll(ingredients);*/
            String reqStr = JSONObject.toJSONString(ingredients);
            
            return sendChy(resUrl,timespan,signStr,reqStr);
        }
        /**
         * 驾驶证查分
         * 根据驾驶证和档案编号查分
         * @param fileNo
         * @param idNo
         * @return
         */
        public  static String getScoreByFileNoAndIdNo(String fileNo,String idNo){
        	Date date = new Date();
        	long l = date.getTime();
        	String timespan = l + ""; //时间戳
        	
        	String resUrl = "/cxy/DriverLicenseScoreService/query";
        	
        	String[] str={fileNo,idNo};
            String paramsStr = getParamsStr(str);//输入参数所有字段的值按字典默认排序，按排序顺序将所有参数值拼接为字符串
            
            String signStr = timespan + paramsStr + secretKey ;
            log.info("signStr:"+signStr);
            signStr= MD5Util.MD5(signStr);//时间戳+参数拼接字符串+密钥的结果进行MD5加密，MD5值需为大写
            
            Map<String, Object> ingredients = new HashMap<String, Object>();
            ingredients.put("fileNo", fileNo);
            ingredients.put("idNo", idNo);
            
            String reqStr = JSONObject.toJSONString(ingredients);
            return sendChy(resUrl,timespan,signStr,reqStr);
        }
        /**
         * 车挡数据
         * 根据车牌查询车辆详细信息
         * @param fileNo
         * @param idNo
         * @return
         */
        public  static String getDetailByCarNumberAndCarCodeAndCarType(String carNumber,String carCode,String carType){
        	Date date = new Date();
        	long l = date.getTime();
        	String timespan = l + ""; //时间戳
        	
        	String resUrl = "/cxy/carrecordservice/query";
        	
        	String[] str={carNumber,carCode,carType};
            String paramsStr = getParamsStr(str);//输入参数所有字段的值按字典默认排序，按排序顺序将所有参数值拼接为字符串
            
            String signStr = timespan + paramsStr + secretKey ;
            log.info("加密前signStr:"+signStr);
            signStr= MD5Util.MD5(signStr);//时间戳+参数拼接字符串+密钥的结果进行MD5加密，MD5值需为大写
            log.info("加密后sign:"+signStr);
            
            Map<String, Object> ingredients = new HashMap<String, Object>();
            ingredients.put("carNumber", carNumber);
            ingredients.put("carCode", carCode);
            ingredients.put("carType", carType);
            
            String reqStr = JSONObject.toJSONString(ingredients);
            return sendChy(resUrl,timespan,signStr,reqStr);
        }
        /**
         * 违章查询
         * @param carnumber
         * @param carcode
         * @param cardrivenumber
         * @return
         */
        public static String queryTrafficViolation(String carnumber,String carcode,String cardrivenumber){
        	/*String server = "test.cx580.com:9000";
			String carnumber = "粤NGG007";
			String carcode = "194336";
			String cardrivenumber = "1111";*/
        	String returnStr ="";
        	try{
        		String url = violationUrl+"/QueryIndex.aspx?userid="+appid
        				+ "&userpwd=" + URLEncoder.encode(secretKey, "UTF-8")
        				+ "&carnumber=" + URLEncoder.encode(carnumber, "UTF-8")
        				+ "&carcode="+URLEncoder.encode(carcode, "UTF-8")
        				+ "&cardrivenumber="+URLEncoder.encode(cardrivenumber, "UTF-8");
        		log.info("请求url:"+url);
        		returnStr = HttpUtil.getInstance().sendHttpPost(url);
    			System.out.println("返回returnStr: " + returnStr);
    			
        	}catch(Exception ex ){
        		ex.getStackTrace();
        		ex.getMessage();
        		log.error(ex,ex);
        	}
        	return returnStr;
        	
        }
        /**
         * 分销（订单）接口
         * @param carnumber
         * @param carcode
         * @param cardrivenumber
         * @return
         */
        public static String queryGateway(String method_type,String orderId,String third_order_id,String order_amount){
        	
        	String resUrl = violationUrl+"/gateway.aspx";
        	
        	//支付
    		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
    		/*dataMap.put("method_type", "pay");
    		dataMap.put("orderId", "2016071200001");
    		dataMap.put("third_order_id", "2016071200001");
    		dataMap.put("order_amount", "123456");*/
    		dataMap.put("method_type", method_type);
    		dataMap.put("orderId", orderId);
    		dataMap.put("third_order_id", third_order_id);
    		dataMap.put("order_amount", order_amount);

    		//注意：method_type = save ，代表下单，如果调用其他接口，请按文档中的说明上送不同的参数
    		String result = "";
			try {
				result = ViolationHttpUtils.sendAndReceive(dataMap, resUrl);
				log.info("请求数据 req:"+dataMap +"\n"+" 得到数据 returnStr==" + result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
        }
        /**
         * 根据日期获取当日有效查询数量
         * @param date   日期格式：2013-02-25
         * @return
         */
        public static String getEffectiveQuerySumByDay(String date){
        	
        	//http://test.cx580.com:9000/QueryStatic.aspx?type=date&userid=xx&userpwd=密码&date=2013-02-25
        	//“date” 表示按日期查询
    		String returnStr ="";
	    	try{
	    		String url = violationUrl+"/QueryStatic.aspx?type=date&userid="+appid
	    				+ "&userpwd=" + URLEncoder.encode(secretKey, "UTF-8")
	    				+ "&date="+date;
	    		log.info("请求url:"+url);
	    		returnStr = HttpUtil.getInstance().sendHttpPost(url);
				System.out.println("返回returnStr: " + returnStr);
				
	    	}catch(Exception ex ){
	    		ex.getStackTrace();
	    		ex.getMessage();
	    		log.error(ex,ex);
	    	}
	    	return returnStr;
        }
        
  
        private static String sendChy(String resUrl ,String timespan,String sign,String reqStr ){
            String returnStr=null;
            try {
                String posturl=url+resUrl+"?appid="+appid+"&timespan="+timespan+"&sign="+sign;
                returnStr = httpClientUtil.doPost(posturl, reqStr);
                
                log.info("posturl:"+posturl);
                log.info("请求数据 req:"+reqStr +"\n"+" 得到数据 returnStr==" + returnStr);
            }catch(Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
            }finally {
                
            }
            return returnStr;
        }
        public static String getParamsStr(String[] str) {
			String temp = "";
			Arrays.sort(str);
			for (String i : str) {
				temp = temp + i;
			}
			log.info(temp);
			return temp;
			
		}
        
		public static void main(String[] args) {
				String carnumber = "粤NGG007";
				String carcode = "194336";
				String cardrivenumber = "1111";
				String returnStr = queryTrafficViolation(carnumber,carcode,cardrivenumber);
				System.out.println("returnStr==" + returnStr);
			
		}
}
