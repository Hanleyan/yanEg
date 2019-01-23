package com.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {

    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(5*60*1000)
            .setConnectTimeout(5*60*1000)
            .setConnectionRequestTimeout(3*60*1000)
            .build();

    private static HttpUtil instance = null;
    private HttpUtil(){}
    public static HttpUtil getInstance(){
        if (instance == null) {
            instance = new HttpUtil();
        }
        return instance;
    }


    public String  sendJson(String url, Map<String, Object> maps){
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        // 创建参数队列

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, (String) maps.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpPost.setHeader("Content-Type","application/json");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);

    }
    

    /**
     * 发送Post请求
     * @param httpPost
     * @return
     */
    public String sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            System.out.println(httpPost.getURI().toString());
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }


    /**
     * 发送Post请求
     * @param url
     * @return
     */
    public String sendHttpPost(String url) {
        String responseContent = null;
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httppost = new HttpPost(url);
        System.out.println(httppost.getRequestLine());
        try {
            // 执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httppost);

            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            // 响应状态
            // System.out.println("status:" + httpResponse.getStatusLine());
            // 判断响应实体是否为空
            if (entity != null) {
                // System.out.println("contentEncoding:" +
                // entity.getContentEncoding());
                responseContent = EntityUtils.toString(entity);
                System.out.println("response content:"
                        + responseContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { // 关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

}
