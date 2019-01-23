package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class HttpClientTestAPP {
    public static void main(String args[]) throws Exception {
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        JSONObject jsonObject = new JSONObject();
        long startTime = new Date().getTime();
        
        //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=003&userid=13";

        //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=105&userid=13";
        
        //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=106&cardid=4&cardcommon=2&cardpartner=1";
       // String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=107&userid=13&searchvalue=商安�?";
        
        /*Users user = new Users();
        user.setCpmCustomerId(8849);
        user.setEmail("25404622@qq.com");
        user.setGroupId(16);
        user.setGroupName("商安�?");
        user.setOrderMail("");
        user.setPhotoPath("3acredit_aaron.jpg");
        user.setPosition("");
        user.setPwd("25f9e794323b453885f5181f1b624d0b");
        user.setRealName("陆歆�?");
        user.setReportMail("");
        user.setRoleId(5);
        user.setStatementMail("");
        user.setTel("123456789");
        user.setUserId(15);
        user.setUserName("3acredit_aaron");
        user.setDelFlag("1");
        user.setMobileNum("13651616272");
        user.setWebsite("www.3abiz.com");
        user.setAreaCode("021");
        jsonObject = jsonObject.fromObject(user);*/
        
        /*BusinessCard card = new BusinessCard();
        card.setGroupId(16);
        card.setUserId(13);
        card.setCardId(4);
        card.setCardCompany("测试名片公司12");//公司名称
        card.setCardName("nancy");//名片�?
        card.setCardPosition("软件�?发工程师");//职称
        card.setCardTel("");//电话
        card.setCardPhone("13651616272");//手机
        card.setCardFax("");//传真
        card.setCardZip("");//邮编
        card.setCardAddress("上海宝山");//地址
        card.setCardEmail("");//名片email;
        card.setCardWebsite("");//网址
        jsonObject = jsonObject.fromObject(card);
*/        
        
        
      /* Orders order = new Orders();
        order.setSubjectName("安徽省银山药业有限公�?");
        order.setCrefoNo("9360008508");
        order.setGroupId(16);
        order.setUserId(13);
        order.setMonitorPeriod("6");
        order.setMoney(10.0);
        jsonObject = jsonObject.fromObject(order);
        String text = jsonObject.toString();
        System.out.println(text);
        text = URLEncoder.encode(text, "UTF-8");
        Integer cardid = 3;
        String url = "http://192.168.1.240:8090/3abiz/services/bizAppService?methodcode=401&order="+text+"&cardid=3";*/
        
        
       //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=202&cardid="+3;
        
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=002&customerName=hanley199900";
        String url = "http://localhost:8080/yanEg/services/bizService?methodcode=012";
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=013&id=3";
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=005&licenseNo=511622199202202827";
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=006&fileNo=320400510925&idNo=320826198111084672";
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=007&carNumber=粤S7A3W9&carCode=LHGGJ764XH2051266&carType=02";
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=008&carnumber="+ URLEncoder.encode("粤NGG007", "UTF-8")+"&carcode=194336&cardrivenumber=1111";
        
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=009&method_type=pay&orderId=2016071200001&third_order_id=2016071200001&order_amount=123456";
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=010&date=2018-10-18";
        
        //String url = "http://localhost:8080/yanEg/services/bizService?methodcode=011";
        //String url = "http://localhost:8080/yanEg/lucene/createLuceneIndex.do";
        //String url = "http://localhost:8080/yanEg/lucene/searchLuceneIndex.do?searchValue=天&pageNo=3&pageSize=10";
		/*String url = "http://test.cx580.com:9000/QueryIndex.aspx?userid=shsaxtest" 
				+ "&userpwd=" + URLEncoder.encode("261917D00A8B4CD080EF0A5C7E244E90", "UTF-8") 
				+ "&carnumber=" + URLEncoder.encode("粤NGG007", "UTF-8") 
				+ "&carcode=194336"  + "&cardrivenumber=1111" ;*/
        
     //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=201&cardid=50";
        
      //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=501";  
        
        
       /*Publicize p = new Publicize();
        p.setUserId(13);
        p.setGroupId(16);
        p.setFullCompany("东莞市晟发印刷科�?有限公司");
        p.setAbbCompany("晟发印刷");
        p.setPublicizeFlag(8);
        p.setIntroduction("印刷");
        p.setCrefoNo("9367149925");
        
        jsonObject = jsonObject.fromObject(p);
        String text = jsonObject.toString();
        System.out.println(text);
        text = URLEncoder.encode(text, "UTF-8");
        String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=502&publicize="+text;*/

       //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=503&publicizeFlag="+8;
       // String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=504&searchvalue=印刷";
        
        
        /*Publicize p = new Publicize();
        p.setPublicizeId(3);
        p.setUserId(13);
        p.setGroupId(16);
        p.setFullCompany("东莞市晟发印刷科�?有限公司(样本)");
        p.setAbbCompany("晟发印刷");
        p.setPublicizeFlag(8);
        p.setIntroduction("印刷、包�?");
        p.setMainProducts("彩印");
        p.setCrefoNo("9367149925");
        p.setAddress("广东省东莞市");
        
        jsonObject = jsonObject.fromObject(p);
        String text = jsonObject.toString();
        System.out.println(text);
        text = URLEncoder.encode(text, "UTF-8");
        String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=505&publicize="+text;*/
        //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=506&publicizeid=1";
        
       //String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=507&userid=13";  
        
      //String url = "http://19.168.1.212:8081/3abiz/services/bizAppService?methodcode=804"; 
       // String url = "http://192.168.1.212:8081/3abiz/services/bizAppService?methodcode=804&data=IL8KKWPvHrFVtpzghQDAMbYzlEuhebLYNfO6%2FGAFzNHhZmYBUb9yW8x2TGqpjcYnMKD4tUejxYLmYC2KZB%2B26B1%2FMddxT6wO9IsfXVB6leSDpoxeqnUs8g%3D%3D";
        
       HttpGet httpGet = new HttpGet(url);
        System.out.println(httpGet.getRequestLine());
        try {
            //执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);//httpGet
            
            //获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            //响应状�??
            //System.out.println("status:" + httpResponse.getStatusLine());
            //判断响应实体是否为空
            if (entity != null) {
                //System.out.println("contentEncoding:" + entity.getContentEncoding());
                System.out.println("response content:" + EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {                //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long endTime = new Date().getTime();
		System.out.println("调接口花费时间：" + (endTime - startTime) + "毫秒.");
    }
}