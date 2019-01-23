package com.test;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;
import com.util.Des3Util;


public class HttpClientTestAPP_desent {
	public static void main(String args[]) throws Exception {
		long start = System.currentTimeMillis();
		String key = "3acredit20171128bibibaba";


		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		JSONObject jsonObject = new JSONObject();


		

	
		
		

/*
String url="http://120.76.205.241:8000/news/sogou?kw=php&site=qq.com&pageToken=30&apikey=IAnakFqceoMPDcxxVYTi7trtZmyLYmVjpXo3dVVIKkRz8uEM406UJLILcK4Rv1ok";
*/
		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		AppCreditQuestionnaire pojo = new AppCreditQuestionnaire();
		pojo.setCompanyName("上海徵万");
		pojo.setFucationStatusCode("002");
		pojo.setPositionStatusCode("002");
		pojo.setMonthlyIncomeStatusCode("002");
		//pojo.setMaritalStatusCode("002");
		pojo.setIndustryStatusCode("002");
		pojo.setEducationStatusCode("002");
		pojo.setChildrenStatusCode("002");
		pojo.setCarsourceStatusCode("002");
		pojo.setUserId(265);
		ingredients.put("creditquestionnaire",pojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=103&data="+URLEncoder.encode(data, "utf-8");*/

		Map<String, Object> ingredients = new HashMap<String, Object>();
		//ingredients.put("licenseNo","511622199202202827");
		ingredients.put("identityName","杨雨恒");
		ingredients.put("mobileNo ","13120656563");
		ingredients.put("identityCardNo","140402199406172037");
		
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString());
		String url = "http://192.168.1.164:8079/services/bizService?methodCode=0001&apiKey=0c255437d4fa455b864361622a8d53a2&data="+URLEncoder.encode(data, "utf-8");


		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid","266");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=105&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid", "2");
		AppOperatorPhonePOJO pojo = new AppOperatorPhonePOJO();
		pojo.setUserName("13651616272");
		pojo.setPassWord("910816");

		ingredients.put("operatorphone",pojo);
		jsonObject.putAll(ingredients);
		System.out.println("参数==="+jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/services/bizService?methodcode=106&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, String> ingredients = new HashMap<String, String>();
		ingredients.put("username", "Nancy");
		ingredients.put("guarantor", "Nancy");
		ingredients.put("mobile", "13651616272");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8083/bibibaba/services/bizService?methodcode=106&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Integer> ingredients = new HashMap<String, Integer>();
		ingredients.put("userId", 165);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=608&data="+URLEncoder.encode(data, "utf-8");
*/
		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		AppGuarantorPOJO appGuarantorPojo = new AppGuarantorPOJO ();
		appGuarantorPojo.setCode("123456");
		appGuarantorPojo.setGuarantorName("郭伟良");
		appGuarantorPojo.setGuarantorCardID("45098119870409543X");
		appGuarantorPojo.setMobile("13717198182");
		appGuarantorPojo.setUserId(263);
		appGuarantorPojo.setRelation("朋友");
		ingredients.put("guarantor",appGuarantorPojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=107&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid", "2");
		AppOperatorPhonePOJO pojo = new AppOperatorPhonePOJO();
		pojo.setUserName("李长菊");
		pojo.setPassWord("910816");
		pojo.setIdentifyingCode("127903");
		ingredients.put("operatorphone",pojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=107&data="+URLEncoder.encode(data, "utf-8");
*/


/*

		Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid", "238");
		ingredients.put("ddplatform", "yes");
		ingredients.put("ofdriver", "old");
		AppOperatorPhonePOJO pojo = new AppOperatorPhonePOJO();
		pojo.setIdentityName("13636310290");
		pojo.setIdentityCardNo("");
		pojo.setPassWord("qwe123456");
		pojo.setUserName("13636310290");//手机号
		pojo.setIdentifyingCode("045594");
		pojo.setToken("45b258a8bd994ddca93e0888051690bb");
		ingredients.put("dididriver",pojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=123&data="+URLEncoder.encode(data, "utf-8");
*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid", "52");
		AppBankPOJO pojo = new AppBankPOJO();
		pojo.setIdentityName("朱万里");
		pojo.setIdentityCardNo("430626197708286314");
		pojo.setCardNo("6228481098374777970");
		pojo.setMobileNO("13278852077");
		//pojo.setIdentifyingCode("127903");
		ingredients.put("bank",pojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=110&data="+URLEncoder.encode(data, "utf-8");
*/



		//String url = "http://192.168.1.212:8083/bibibaba/services/bizService?methodcode=111";

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid", "2");
		AppProvidentFundPOJO pojo = new AppProvidentFundPOJO();
		pojo.setArea("3100");
		pojo.setUsername("shijiadaniel");
		pojo.setPassword("06731259");
		pojo.setOtherInfo("310106199304162816");
		pojo.setRealName("时佳");
		ingredients.put("providentfund",pojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8083/bibibaba/services/bizService?methodcode=112&data="+URLEncoder.encode(data, "utf-8");*/


		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid", "2");
		AppCreditCenterPOJO pojo = new AppCreditCenterPOJO();
		pojo.setUsername("shijia0416");
		pojo.setPassword("Sj123456");
		pojo.setRealName("时佳");
		pojo.setIdentityNo("310106199304162816");
		pojo.setMobile("13248259262");
		ingredients.put("creditcenter",pojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8083/bibibaba/services/bizService?methodcode=113&data="+URLEncoder.encode(data, "utf-8");*/


		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid", "2");
		AppCreditCenterPOJO pojo = new AppCreditCenterPOJO();
		pojo.setUsername("fuchen");
		pojo.setPassword("123456");
		pojo.setRealName("彭晓芳");
		pojo.setMobile("1231545");
		pojo.setToken("54665685");
		pojo.setIdentifyingCode("956551535");
		ingredients.put("creditcenter",pojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/bibibaba/services/bizService?methodcode=114&data="+URLEncoder.encode(data, "utf-8");*/


		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid", "1");
		List<AppAnswerCenterPOJO> answerCenter_pojos = new ArrayList<AppAnswerCenterPOJO>();
		AppAnswerCenterPOJO pojo1 = new AppAnswerCenterPOJO();
		pojo1.setOption("1");
		pojo1.setQuestionNo("2");
		answerCenter_pojos.add(pojo1);
		ingredients.put("answer",pojo1);
		String token = "4654646853230636";
		ingredients.put("token",token);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=117&data="+URLEncoder.encode(data, "utf-8");
*/

		/*String url = "http://192.168.1.164:8083/services/bizService?methodcode=201";*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("carbrandid","1");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/services/bizService?methodcode=202&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> notiftMap = new HashMap<String, Object>();
		notiftMap.put("userId","973");
		jsonObject.putAll(notiftMap);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=119&data=" + URLEncoder.encode(data, "utf-8");
*/
		/*Map<String, Object> Map = new HashMap<String, Object>();
		List list = new ArrayList();
		for (int i = 1; i < 11; i++) {
			Answer an = new Answer(19977,i,-1,false);
			list.add(an);
		}
		Map.put("answers",list);
		jsonObject.putAll(Map);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=120&data=" + URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("cartypeid","100001");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/services/bizService?methodcode=203&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("carmodelid","1");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/services/bizService?methodcode=204&data="+URLEncoder.encode(data, "utf-8");*/


		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		AppGarage garage = new AppGarage();
		garage.setUserId(2);
		garage.setCarBrandId(1);
		garage.setCarPrdocutId(2);
		garage.setCarTypeId(100009);
		garage.setCarModelId(424);
		garage.setCarColor("大红");
		garage.setCarVin("vin951753852456");

		ingredients.put("garage",garage);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/services/bizService?methodcode=301&data="+URLEncoder.encode(data, "utf-8");*/


		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid","2");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/services/bizService?methodcode=302&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("carnewid","1");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/services/bizService?methodcode=303&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("garageid","10");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8080/services/bizService?methodcode=304&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("cartypeid","100006");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8083/bibibaba/services/bizService?methodcode=305&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("cartypeid","3999");
		ingredients.put("pagesize","2");
		ingredients.put("pageno","1");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=307&data="+URLEncoder.encode(data, "utf-8");

*/
		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userId","2");
		ingredients.put("mobileNo","123465789");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=121&data="+URLEncoder.encode(data, "utf-8");
*/


		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		AppFlowLog pojo = new AppFlowLog();
		pojo.setUserId(255);
		pojo.setFlowCode("040");
		pojo.setFlowValue("1");
		ingredients.put("flow",pojo);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=401&data="+URLEncoder.encode(data, "utf-8");
*/
		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid","236");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=402&data="+URLEncoder.encode(data, "utf-8");
*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flowCode","020");
		map.put("flowValue","｛envrwegaga｝");
		map.put("userId",48);
		ingredients.put("flow",map);
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=404&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("flowlogid","3");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.212:8083/bibibaba/services/bizService?methodcode=403&data="+URLEncoder.encode(data, "utf-8");*/


		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid","236");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=405&data="+URLEncoder.encode(data, "utf-8");
*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userid","255");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=406&data="+URLEncoder.encode(data, "utf-8");*/

		/*Map<String, Object> ingredients = new HashMap<String, Object>();
		ingredients.put("userId","255");
		jsonObject.putAll(ingredients);
		System.out.println(jsonObject.toString());
		String data = Des3Util.encrypt(jsonObject.toString(), key);
		String url = "http://192.168.1.164:8083/services/bizService?methodcode=609&data="+URLEncoder.encode(data, "utf-8");
*/
		System.out.println(url);

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
				System.out.println("response content:"
						+ EntityUtils.toString(entity));
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
		long end = System.currentTimeMillis();
		System.out.println("花费时间" + (end - start));
	}
}