package com.service;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.util.EnumMessageCode;
import com.util.JsonContent;


@Repository
public class APPServer extends Thread{

	private static final Logger log = Logger.getLogger(APPServer.class);

	@Autowired
	AppService appService;
	
	public void run(HttpServletRequest req){
		System.out.println(" 线程运行开始");
		
		try {
			JsonContent json = queryCustomerSize(req);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public JsonContent addCustomer(HttpServletRequest req)throws Exception{
		JsonContent json = new JsonContent();
		//String userPhone = Tool.decryptionURL(req, "userphone");
		long startTime = new Date().getTime();
		Boolean b = appService.addCustomer(json);
		if(b){
			json.setCode(EnumMessageCode.code1.getId());
		} else {
			json.setCode(EnumMessageCode.code2.getId());
		}
		long endTime = new Date().getTime();
		System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
		return json;
	}
	
	public JsonContent addHanzi(HttpServletRequest req)throws Exception{
		JsonContent json = new JsonContent();
		//String userPhone = Tool.decryptionURL(req, "userphone");
		long startTime = new Date().getTime();
		Boolean b = appService.addHanzi(json);
		if(b){
			json.setCode(EnumMessageCode.code1.getId());
		} else {
			json.setCode(EnumMessageCode.code2.getId());
		}
		long endTime = new Date().getTime();
		System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
		return json;
	}
	
	public JsonContent addAppUsers(HttpServletRequest req)throws Exception{
		JsonContent json = new JsonContent();
		//String userPhone = Tool.decryptionURL(req, "userphone");
		long startTime = new Date().getTime();
		Boolean b = appService.addAppUsers(json);
		if(b){
			json.setCode(EnumMessageCode.code1.getId());
		} else {
			json.setCode(EnumMessageCode.code2.getId());
		}
		long endTime = new Date().getTime();
		System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
		return json;
	}
	
	public JsonContent upAppUsers(HttpServletRequest req)throws Exception{
		long startTime = new Date().getTime();
		JsonContent json = new JsonContent();
		//String userPhone = Tool.decryptionURL(req, "userphone");
		String id =req.getParameter("id");
		
		Boolean b = appService.upAppUsers(json,Integer.parseInt(id));
		if(b){
			json.setCode(EnumMessageCode.code1.getId());
		} else {
			json.setCode(EnumMessageCode.code2.getId());
		}
		long endTime = new Date().getTime();
		System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
		return json;
	}
	
	public JsonContent upAppUsersByThread(HttpServletRequest req)throws Exception{
		long startTime = new Date().getTime();
		JsonContent json = new JsonContent();
		//String userPhone = Tool.decryptionURL(req, "userphone");
		String id =req.getParameter("id");
		
		Boolean b = appService.upAppUsersByThread(json,Integer.parseInt(id));
		if(b){
			json.setCode(EnumMessageCode.code1.getId());
		} else {
			json.setCode(EnumMessageCode.code2.getId());
		}
		long endTime = new Date().getTime();
		System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
		return json;
	}
	
	public JsonContent queryCustomer(HttpServletRequest req)throws Exception{ 
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();
		//String userPhone = Tool.decryptionURL(req, "customerName");
		String customerName = req.getParameter("customerName");
		String customerMail = req.getParameter("customerMail");
		String customerPhone = req.getParameter("customerPhone");
		String customerAddress = req.getParameter("customerAddress");
		
		log.info(" 查询 customerName=="+customerName);
		if(!StringUtils.isBlank(customerName) || !StringUtils.isBlank(customerMail) || !StringUtils.isBlank(customerPhone) ||!StringUtils.isBlank(customerAddress)){
			Boolean b = appService.queryCustomer(null,json, customerName,customerMail, customerPhone, customerAddress);
			if(b){
				json.setCode(EnumMessageCode.code1.getId());
			} else {
				json.setCode(EnumMessageCode.code2.getId());
			}
			long endTime = new Date().getTime();
			System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
			return json;
		}
		json.setCode(EnumMessageCode.code7.getId());
		return json;
	}

	public JsonContent queryCustomerSize(HttpServletRequest req)
			throws Exception {
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();

		Boolean b = appService.queryCustomerSize(json);
		if (b) {
			json.setCode(EnumMessageCode.code1.getId());
		} else {
			json.setCode(EnumMessageCode.code2.getId());
		}
		long endTime = new Date().getTime();
		System.out.println("花费时间：" + (endTime - startTime) + "毫秒.");
		return json;

	}
	
	public JsonContent queryCustomerByLucene(HttpServletRequest req)throws Exception{ //queryCustomerByLucene
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();
		//String userPhone = Tool.decryptionURL(req, "customerName");
		String customerName = req.getParameter("customerName");
		String customerMail = req.getParameter("customerMail");
		String customerPhone = req.getParameter("customerPhone");
		String customerAddress = req.getParameter("customerAddress");
		
		log.info(" 查询 customerName=="+customerName);
		if(!StringUtils.isBlank(customerName) || !StringUtils.isBlank(customerMail) || !StringUtils.isBlank(customerPhone) ||!StringUtils.isBlank(customerAddress)){
			Boolean b = appService.queryCustomerByLucene(null,json, customerName,customerMail, customerPhone, customerAddress);
			if(b){
				json.setCode(EnumMessageCode.code1.getId());
			} else {
				json.setCode(EnumMessageCode.code2.getId());
			}
			long endTime = new Date().getTime();
			System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
			return json;
		}
		json.setCode(EnumMessageCode.code7.getId());
		return json;
	}

	
	public JsonContent getDetaliByLicenseNo(HttpServletRequest req)throws Exception{ 
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();
	
		String licenseNo = req.getParameter("licenseNo");
		
		log.info(" 查询 licenseNo=="+licenseNo);
		if(!StringUtils.isBlank(licenseNo)){
			Boolean b = appService.getDetaliByLicenseNo(json, licenseNo);
			if(b){
				json.setCode(EnumMessageCode.code1.getId());
			} else {
				json.setCode(EnumMessageCode.code2.getId());
			}
			long endTime = new Date().getTime();
			System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
			return json;
		}
		json.setCode(EnumMessageCode.code7.getId());
		return json;
	}

	public JsonContent getScoreByFileNoAndIdNo(HttpServletRequest req)throws Exception{ 
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();
	
		String fileNo = req.getParameter("fileNo");
		String idNo = req.getParameter("idNo");
		
		log.info(" 驾驶证查分 fileNo=="+fileNo+" idNo:"+idNo);
		if(!StringUtils.isBlank(fileNo) && !StringUtils.isBlank(idNo)){
			Boolean b = appService.getScoreByFileNoAndIdNo(json,fileNo, idNo);
			if(b){
				json.setCode(EnumMessageCode.code1.getId());
			} else {
				json.setCode(EnumMessageCode.code2.getId());
			}
			long endTime = new Date().getTime();
			System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
			return json;
		}
		json.setCode(EnumMessageCode.code7.getId());
		return json;
	}

	
	public JsonContent getDetailByCarNumberAndCarCodeAndCarType(HttpServletRequest req)throws Exception{ 
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();
	
		String carNumber = req.getParameter("carNumber");
		String carCode = req.getParameter("carCode");
		String carType = req.getParameter("carType");
		
		log.info("  车挡数据   carNumber=="+carNumber+" carCode:"+carCode + " carType:"+carType);
		if(!StringUtils.isBlank(carNumber) && !StringUtils.isBlank(carCode) && !StringUtils.isBlank(carType)){
			Boolean b = appService.getDetailByCarNumberAndCarCodeAndCarType(json,carNumber, carCode,carType);
			if(b){
				json.setCode(EnumMessageCode.code1.getId());
			} else {
				json.setCode(EnumMessageCode.code2.getId());
			}
			long endTime = new Date().getTime();
			System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
			return json;
		}
		json.setCode(EnumMessageCode.code7.getId());
		return json;
	}

	public JsonContent queryTrafficViolation(HttpServletRequest req)throws Exception{ 
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();
	
		String carnumber = req.getParameter("carnumber");
		String carcode = req.getParameter("carcode");
		String cardrivenumber = req.getParameter("cardrivenumber");
		
		log.info("  查违章   carnumber=="+carnumber+" carcode:"+carcode + " cardrivenumber:"+cardrivenumber);
		if(!StringUtils.isBlank(carnumber) && !StringUtils.isBlank(carcode) && !StringUtils.isBlank(cardrivenumber)){
			Boolean b = appService.queryTrafficViolation(json,carnumber, carcode,cardrivenumber);
			if(b){
				json.setCode(EnumMessageCode.code1.getId());
			} else {
				json.setCode(EnumMessageCode.code2.getId());
			}
			long endTime = new Date().getTime();
			System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
			return json;
		}
		json.setCode(EnumMessageCode.code7.getId());
		return json;
	}

	
	public JsonContent queryGateway(HttpServletRequest req)throws Exception{ 
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();
	
		String method_type = req.getParameter("method_type");
		String orderId = req.getParameter("orderId");
		String third_order_id = req.getParameter("third_order_id");
		String order_amount = req.getParameter("order_amount");
		
		log.info(" 分销（订单）接口 method_type"+method_type+"  orderId=="+orderId+" third_order_id:"+third_order_id + " order_amount:"+order_amount);
		if(!StringUtils.isBlank(orderId) && !StringUtils.isBlank(third_order_id) && !StringUtils.isBlank(order_amount)){
			Boolean b = appService.queryGateway(json,method_type,orderId, third_order_id,order_amount);
			if(b){
				json.setCode(EnumMessageCode.code1.getId());
			} else {
				json.setCode(EnumMessageCode.code2.getId());
			}
			long endTime = new Date().getTime();
			System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
			return json;
		}
		json.setCode(EnumMessageCode.code7.getId());
		return json;
	}
	
	public JsonContent getEffectiveQuerySumByDay(HttpServletRequest req)throws Exception{ 
		JsonContent json = new JsonContent();
		long startTime = new Date().getTime();
	
		String date = req.getParameter("date");
		
		log.info(" 根据日期获取当日有效查询数量  date:"+date);
		if(!StringUtils.isBlank(date)){
			Boolean b = appService.getEffectiveQuerySumByDay(json,date);
			if(b){
				json.setCode(EnumMessageCode.code1.getId());
			} else {
				json.setCode(EnumMessageCode.code2.getId());
			}
			long endTime = new Date().getTime();
			System.out.println("花费时间："+(endTime-startTime)+"毫秒.");
			return json;
		}
		json.setCode(EnumMessageCode.code7.getId());
		return json;
	}
	
}
