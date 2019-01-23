package com.cilent;

/*import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;*/




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.service.AppService;



public class httpClientUtil {	
	
	protected static final int  httpConnectionTimeOut = 30;
	
	//protected final static	Logger log = LoggerFactory.getLogger(httpClientUtil.class);
	private static final Logger log = Logger.getLogger(httpClientUtil.class);
	
	/*
	public static String post(String url, String params) throws IOException{

    String body = null;
    HttpClient httpClient = null;
    PostMethod method = null;
     

    try{
        httpClient = new HttpClient();
        method = new PostMethod(url); 
        // 链接超时30秒 
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(1000* httpConnectionTimeOut);

        // 读取超时30秒

        httpClient.getHttpConnectionManager().getParams().setSoTimeout(1000* httpConnectionTimeOut); //         

        log.info("create http post:"  + url);

        RequestEntity requestEntity = new ByteArrayRequestEntity(params.getBytes("UTF-8"));      

        method.setRequestEntity(requestEntity);

        if(method.getStatusCode() == HttpStatus.SC_OK){
            body = method.getResponseBodyAsString();
        }else{
            log.error("method.getStatusCode()"+ method.getStatusCode());

        }
    }catch(IOException e){
        log.error("IOException", e);
    }catch(Exception e){
        log.error("Exception", e);

    }finally{
        if(method != null){
            method.releaseConnection();
        }

    }

    return body;

}*/
	
	
	/**
	 * post请求（用于请求json格式的参数）
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, String params) throws Exception {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost   
    	httpPost.setHeader("Accept", "application/json"); 
    	httpPost.setHeader("Content-Type", "application/json");
    	String charSet = "UTF-8";
    	StringEntity entity = new StringEntity(params, charSet);
    	httpPost.setEntity(entity);        
        CloseableHttpResponse response = null;
        
        try {
        	
        	response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
            	HttpEntity responseEntity = response.getEntity();
            	String jsonString = EntityUtils.toString(responseEntity);
            	return jsonString;
            }
            else{
				 log.error("请求返回:"+state+"("+url+")");
			}
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return null;
	}


 


}
