package com.cilent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;









import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.util.MD5Util;

public class testCyh {

	public static void main(String[] args) throws Exception {
    	/*String[] strArray = new String[] {"粤S7A3W9","02","LHGGJ764XH2051266"};
    	String res = getParamsStr(strArray);
    	System.out.println(res);
    	System.out.println(MD5Util.md5Capital(res));
    	System.out.println(MD5Util.md5(res));
    	System.out.println(MD5Util.string2MD5(res));*/
		
		
		String server = "test.cx580.com:9000";
		String carnumber = "粤NGG007";
		String carcode = "194336";
		String cardrivenumber = "1111";
		
		CyhClient c = new CyhClient();
		String a = c.queryTrafficViolation(carnumber,carcode,cardrivenumber);
		System.out.println("返回a: " + a);
		
		/*HttpClient httpClient = new HttpClient();
		GetMethod getMethod = null;
		String url = "http://" + server + "/QueryIndex.aspx?userid=" 
			+ "shsaxtest" + "&userpwd=" + URLEncoder.encode("261917D00A8B4CD080EF0A5C7E244E90", "UTF-8") 
			+ "&carnumber=" + URLEncoder.encode(carnumber, "UTF-8") 
			+ "&carcode=" + carcode + "&cardrivenumber=" + cardrivenumber;
		
		getMethod = new GetMethod(url);
		getMethod.getParams().setParameter("http.method.retry-handler",
				new DefaultHttpMethodRetryHandler());

		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != 200) {
			System.out.println( "Error:" + statusCode);
		}
		//InputStream responseBody = getMethod.getResponseBodyAsStream();
		//String json = inputStreamToString(responseBody);
		byte[] responseBody = getMethod.getResponseBody();
		String json = new String(responseBody, "UTF-8");
		System.out.println("返回Json" + json);*/
		
		
		
		
    }
	
	
	
	
	
	
	
	
	
	
	public static String getParamsStr(String[] str) {
		String temp = "";
		// String[] strArray = new String[] { "粤A88888", "02", "ysls" };
		Arrays.sort(str);
		for (String i : str) {
			temp = temp + i;
		}
		return temp;
	}
}
