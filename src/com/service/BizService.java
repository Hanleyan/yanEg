package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import com.alibaba.fastjson.JSONObject;
import com.util.EnumMessageCode;
import com.util.JsonContent;

/**
 * 移动端登陆接口
 * 
 * @author DELL
 * 
 */
public class BizService extends HttpServlet{

	private static final Logger log = Logger.getLogger(BizService.class);


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public BizService() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		
			String methodcode = req.getParameter("methodcode");
			String data = req.getParameter("data");
			log.info("methodcode:"+methodcode+"  data:"+data);

			JsonContent json = new JsonContent();
			try {
				if (null == methodcode || methodcode.trim().equals("")) {
					json.setCode(EnumMessageCode.code2.getId());
				} else {
					APPServer service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(APPServer.class);
					if (methodcode.equals("001")) {
						json = service.addCustomer(req);
					}else if (methodcode.equals("002")) {
						json = service.queryCustomer(req);
					}else if(methodcode.equals("003")){
						json = service.queryCustomerSize(req);
					}else if (methodcode.equals("004")) {
						json = service.queryCustomerByLucene(req);
					}else if (methodcode.equals("005")) {
						json = service.getDetaliByLicenseNo(req);// 驾驶证数据
					}else if (methodcode.equals("006")) {
						json = service.getScoreByFileNoAndIdNo(req);//驾驶证查分
					}else if (methodcode.equals("007")) {
						json = service.getDetailByCarNumberAndCarCodeAndCarType(req);// 车挡数据
					}else if (methodcode.equals("008")) {
						json = service.queryTrafficViolation(req);// 查违章
					}else if (methodcode.equals("009")) {
						json = service.queryGateway(req);// 查  分销（订单）接口
					}else if (methodcode.equals("010")) {
						json = service.getEffectiveQuerySumByDay(req);// 根据日期获取当日有效查询数量
					}else if (methodcode.equals("011")) {
						json = service.addHanzi(req);
					}else if (methodcode.equals("012")) {
						json = service.addAppUsers(req);
					}else if (methodcode.equals("013")) {
						json = service.upAppUsers(req);
					}else if (methodcode.equals("014")) {
						json = service.upAppUsersByThread(req);
					}
					

					else {
						json.setCode(EnumMessageCode.code2.getId());
					}

				}
			}catch (Exception e){
				e.printStackTrace();
				log.error(e.getStackTrace());
				log.error(e.getMessage());
				log.error(e,e);
				json.setCode(EnumMessageCode.code0.getId());
			}finally {
				json.setMessage(EnumMessageCode.getDescById(json.getCode()));
				resp.setContentType("text/plain");
				PrintWriter out = resp.getWriter();
				String text = JSONObject.toJSONString(json);
				log.info("methodcode=="+methodcode+"---text=="+text);
				out.print(text);
				out.flush();
				out.close();
			}
		
	}

}
