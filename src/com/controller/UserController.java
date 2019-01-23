package com.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.crypto.macs.HMac;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.entity.RegJdUser;

@Controller
public class UserController {

	private static final Log log = LogFactory.getLog(UserController.class);
	
	@RequestMapping("/login.do" )
	@ResponseBody
	public String login(HttpSession session, HttpServletRequest request,
						HttpServletResponse response, PrintWriter writer  ) {   //String userName, String passWord
		//session.removeAttribute("error");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		Map<String,String> map = new HashMap<String,String>();
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		System.out.println(userName+ "--" + passWord);
		
		String flag="";
		try {
			//response.setContentType("text/html; charset=UTF-8");
			//-------------临时用户-----start--------
			if ("fjj".equals(userName)  && "123456".equals(passWord)) {
				//writer.print("登入成功");
				flag="登入成功";
			}else{
				//writer.print("账号或密码不正确");
				flag="账号或密码不正确";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		//map.put("flag", flag);
		writer.print(flag);
		//writer.write(flag);
		//"{flag:"+flag+"}"
		return null;
	}


	@RequestMapping("/index.do" )
	public String index(HttpSession session, HttpServletRequest request,
						HttpServletResponse response, PrintWriter writer, ModelMap map) {

		return "index";
	}
	
	@RequestMapping("/login1.action")
	@ResponseBody
	public Object login1(HttpServletRequest request, HttpServletResponse response) {

	    // 将用户名和密码保存到user

	    
	    String uname = request.getParameter("uname");
	    String pwd = request.getParameter("pwd");

	    String flag = "false";
	    // 判断登陆是否成功
	    if("fjj".equals(uname)  && "123456".equals(pwd)){
	        flag = "true";

	    }else{
	        flag = "false";
	    }

	    return flag;
	}
	
	@RequestMapping("/submitRegJDUser.do")
	public void submitRegJDUser(HttpServletRequest request, HttpServletResponse response ,RegJdUser r,PrintWriter write) {

	   log.info(" 有了："+JSON.toJSONString(r));
	   write.write("有了");
	   
	}
	
}
