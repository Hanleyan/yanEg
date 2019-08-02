package  com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class Tool {
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(Tool.class);
	public static final String MSG_SAVE = "保存成功";
	public static final String MSG_LOGIN = "登录失败";
	
	
	public static String luceneIndexPath = "";
	public static String reportLegelIndexPath = "";
	
	//final static String deskey = "3abiz123453abiz123453abi";
	  //final static String deskey = "3acredit20171128bibibaba";
	
	//public static final String bucketName = "risk-link";
	
	
	DecimalFormat df = new DecimalFormat("########0.00");
	
	public Double formatDouble(Double value){
		String str = df.format(value);
		System.out.println(str);
		return Double.parseDouble(str);
	}
	
	/**
	 * js弹出
	 * @param str
	 * @return
	 */
	public static String alertMsg(String str){
		StringBuffer msg = new StringBuffer( "<script> alert('");
		msg.append(str);
		msg.append("');");
		msg.append("</script>");
		return msg.toString();
	}
	
	
	
	/** 
	 * 获取服务的url基本地址
	 * @param request
	 * @return */ 
	public static String getServerPath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/"; 
		return basePath; 
	}
	
	
	/**
	 * 用户IP
	 * @param request
	 * @return String
	 */
	public String getIP(HttpServletRequest request){
		//获取IP
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	
	
	public static void logInfo(String option,Logger log, String hql, Object object){
		log.info(option+" "+"HQL = "+hql+" Object ="+ JSONObject.toJSONString(object));
	}
	
	public static void logError(String option,Logger log, Exception e, Object object){
		log.error(option+" "+"错误 = "+ e+ " 其他信息 = "+object);
	}
	
	/**
	 * 解析数据
	 * @param req
	 * @param fileName
	 * @return
	 */
	public static String decryptionURL(HttpServletRequest req, String fileName){
		//String key = "3abiz123";
		String data = req.getParameter("data");
		
		System.out.println("data = "+data);
		String value = "";
		if(null != data && !data.trim().equals("")){
			try {
				String decdata;
				
				//data = URLDecoder.decode(data,"utf-8") ;
				decdata = Des3Util.decrypt(data);

				//System.out.println("decdata="+decdata);
				
				JSONObject  dataObj =  JSONObject.parseObject(decdata);
				value = dataObj.getString(fileName);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return value;
	}




	
	/**
	 * 转换身份证
	 * @param str
	 * @return
	 */
	public static String convertID(String str){
        if(null != str && str.length()>4){
            int length = str.length();
            str = str.substring(0, length-4);
            str = str + "****";
            return str;
        }
        return str;

    }
	
	/**
	 * 转换名字
	 * @param str
	 * @return
	 */
	public static String convertName(String str){
		String value = "";
        if(null != str && str.length()>=2){
        	if(str.length() == 2){
        		str = str.substring(0, 1);
        		value = str +"*";
        	}else if(str.length() == 3){
        		String str1 = str.substring(0, 1);
        		value = str1 +"*";
        		String str2 = str.substring(2, str.length());
        		value = value + str2;
        	}else{
        		String str1 = str.substring(0, str.length() -2);
        		value = str1 +"*";
        		String str2 = str.substring(str.length() -2, str.length() -1);
        		value = value + str2;
        	}
        	
            
        }
        return value;

    }
	
	/**
	 * 转换性别
	 * @param value
	 * @return
	 */
	public static String convertGender(Integer value){
        String str = "";
        if(null != value && value == 1){
        	str = "男";
        }else if(null != value && value == 2){
        	str = "女";
        }else{
        	str = "未知";
        }
        return str;

    }
	
	/**
	 * 身份证核实一致性
	 * @param value
	 * @return
	 */
	public static String convertIdcardVerifysituation(String  value){
        String str = "";
        if(null != value && value.equals("1")){
        	str = "一致";
        }
        if(null != value && value.equals("2")){
        	str = "不一致";
        }else{
        	str = "未知";
        }
        return str;

    }
	
	/**
	 * 人像核实一致性
	 * @param value
	 * @return
	 */
	public static String convertImageVerifySituation(String  value){
        String str = "";
        if(null != value && value.equals("1")){
        	str = "一致";
        }
        if(null != value && value.equals("2")){
        	str = "不一致";
        }else{
        	str = "未知";
        }
        return str;

    }
	
	
	/**
	 * 电话类型
	 * @param value
	 * @return
	 */
	public static String convertPhoneType(Integer  value){
        String str = "";
        if(null != value && value == 1){
        	str = "手机";
        }
        if(null != value && value == 2){
        	str = "座机";
        }else{
        	str = "未知";
        }
        return str;

    }
	
	/**
	 * 手机号认证
	 * @param value
	 * @return
	 */
	public static String convertPhoneAuthentication(Integer  value){
        String str = "";
        if(null != value  && value == 1){
        	str = "手机";
        }
        if(null != value && value == 2){
        	str = "座机";
        }else{
        	str = "未知";
        }
        return str;

    }
	
	/**
	 * 电话号是否在用
	 * @param value
	 * @return
	 */
	public static String convertIsUser(Integer  value){
        String str = "";
        if(null != value  && value == 1){
        	str = "是";
        }
        if(null != value && value == 2){
        	str = "否";
        }else{
        	str = "未知";
        }
        return str;

    }
	
	/**
	 * 是否现居住地址
	 * @param value
	 * @return
	 */
	public static String convertIsLive(Integer  value){
        String str = "";
        if(null != value  && value == 1){
        	str = "是";
        }
        if(null != value && value == 2){
        	str = "否";
        }else{
        	str = "未知";
        }
        return str;

    }
	
	
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		return temp;
	}


	/**
	 *
	 * @return WebRoot目录的绝对路径
	 */
	@SuppressWarnings("unused")
	public static String getRootPath() {
		String classPath = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		String rootPath = "";

		/** For Windows */
		if ("\\".equals(File.separator)) {
			String path = classPath.substring(1,
					classPath.indexOf("/WEB-INF/classes"));
			// rootPath = path.substring(0, path.lastIndexOf("/"));
			rootPath = rootPath.replace("/", "\\");
		}

		/** For Linux */
		if ("/".equals(File.separator)) {
			String path = classPath.substring(0,
					classPath.indexOf("/WEB-INF/classes"));
			//rootPath = path.substring(0, path.lastIndexOf("/"));
			rootPath = rootPath.replace("\\", "/");
		}
		return rootPath;
	}


	public static String isNull(String str){
		String returnStr = "";
		try {
			if(!StringUtils.isEmpty(str)){
				returnStr = str;
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
			return returnStr;
		
	}

	public static String forOrderNo(Integer num,Integer temp){ //temp位数    forOrderNo
		String str = num+"";
		int count =str.length();
		if(count<temp) {
			for (int i = 0; i < count; i++) {
				str = "0" + str;
				count = str.length();
				if (count == temp) {
					break;
				}
			}
		}
		return str;
	}
	/*public static String orderNoForDealerId(Integer dealerId){
		String dealerid = dealerId+"";
		int count =dealerid.length();
		if(count<4) {
			for (int i = 0; i < count; i++) {
				dealerid = "0" + dealerid;
				count = dealerid.length();
				if (count == 4) {
					break;
				}
			}
		}
		return dealerid;
	}*/
	public static String orderNoForFucationCode(String fucationStatusCode){
		int a = Integer.parseInt(fucationStatusCode);
		String code = a+"";
		return code;
	}

	public static void getReportIndexPath(){
		//reportIndexPath = "D://Lucene//lucene_2018-9-15";
		//reportIndexPath = "J://lucene_2018-9-12";
		Properties pro = new Properties();

		InputStream in = null;
		try {
			in=Tool.class.getClassLoader().getResourceAsStream("/lucene.properties");
			pro.load(in);
			in.close();
			luceneIndexPath = pro.getProperty("index.path");//常数
			if(null==luceneIndexPath)
				throw new RuntimeException("lucene.properties 属性读取不到");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(in!=null){
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//截取数字
	//判断字符串是否包含数字，包含数字返回  包含字符串长度
	public static int getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String intStr = matcher.group(0);
			return intStr.length();
		}
		return 0;
	}

	/**
	 * 过滤特殊字符
	 * @param str
	 * @return
	 */
	public static String FilterString(String str){
		String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";
		if(null != str && !str.trim().equals("")){
			Pattern p   =   Pattern.compile(regEx);
			Matcher m   =   p.matcher(str);
			String result = m.replaceAll(" ").trim();
			result = result.replaceAll(" ", "");
			//return result.replaceAll(" ", "");
			return result;
		}
		return str;
	}
	
	/**
	 * 字符转Utf-8
	 * */
	public static String ios8859ByUtf8(String str){
		String returnStr = "";
		try{
			if(str.equals(new String(str.getBytes("iso8859-1"), "iso8859-1")))
			{
				str=new String(str.getBytes("iso8859-1"),"utf-8");
			}
			returnStr = str;

			//returnStr = new String(str.getBytes("ISO-8859-1"),"utf-8");
			//returnStr = returnStr.trim();
		}catch(Exception ex){
			//ex.printStackTrace();
		}
		return returnStr;
	}
	/** 10个随机汉字？*/
	public static String getTenHanziByRandom() throws UnsupportedEncodingException{
		
		String str = "";
		for(int i=0;i<10;i++){
			String stem = "";
	        int highPos, lowPos;
	        Random random = new Random();
	        highPos = (176 + Math.abs(random.nextInt(39)));
	        lowPos = 161 + Math.abs(random.nextInt(93));
	        byte[] b = new byte[2];
	        b[0] = (new Integer(highPos)).byteValue();
	        b[1] = (new Integer(lowPos)).byteValue();
	        stem = new String(b, "GB2312");
	        str += stem;
	        
		}

		return str;
		
	}
	/** 订单编号*/
	public static String getOrderNum(Integer userId)  {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderNum = "";
		
		orderNum = format.format(new Date()) + userId+generateTextCode(6);
		return orderNum;
		
	}
	
	/**
	 * 生成纯数字验证码
	 * length 长度，默认为6
	 */
	public static String generateTextCode(int length){
		if(length == 0){
			length = 6;
		}
		
		StringBuffer code=new StringBuffer();
		int i=0;
		Random r=new Random();
		
		while(i<length){
			int t=r.nextInt(9);
			code.append(t);
			i++;
		}
		return code.toString();
	}
	
}
