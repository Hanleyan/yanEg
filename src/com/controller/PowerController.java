package com.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.entity.power.Position;
import com.entity.power.Users;
import com.service.PowerService;
import com.util.BaseController;
import com.util.EnumMessageCode;
import com.util.JsonContent;

/**
 * @author hanley
 * @date 2018/12/13 15:28
 * 风萧萧兮易水寒
 */
@Controller
@RequestMapping("power/")
public class PowerController extends BaseController{
    private static final Log log = LogFactory.getLog(PowerController.class);

    @Autowired
    private PowerService powerService;

    @RequestMapping("powerLogin.do")
    //@ResponseBody
    public String powerLogin(HttpServletRequest request, HttpServletResponse response,ModelMap m , String username,String pwd){  // PrintWriter writer,
    	log.info("username："+username+" pwd:"+pwd);
    	Users u =  powerService.login(m,username,pwd);
        log.info("登入用户u："+JSON.toJSONString(u));
        /*if(b) writer.print("success");
        else writer.print("用户名或密码error");
        return null;*/
        /*if(b) return "success";
        else return "用户名或密码error";*/
        if(u != null){
        	setSessionUser(request, u);
        	
        	String url ="";
        	try {
				//url =  "redirect:entryPowerIndex.do?username=" +URLEncoder.encode(username,"UTF-8")+"&pwd="+URLEncoder.encode(pwd,"UTF-8");
				url =  "redirect:entryPowerIndex.do?username="+username+"&pwd="+pwd;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e,e);
			}
        	return url;
        }else{
        	m.addAttribute("username", username);
        	m.addAttribute("pwd", pwd);
        	m.addAttribute("msg", "用户名或密码error");
        	return "power/powerLogin";
        } 

    }
    
    @RequestMapping("entryPowerIndex.do")
    public String entryPowerIndex(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,ModelMap m , String username,String pwd){
    	log.info("username："+username+" pwd:"+pwd);
    	powerService.entryPowerIndex(m ,username,pwd);
    	return "power/powerIndex";
    }
    

    @RequestMapping("entryTakeAction")
    public String entryTakeAction(ModelMap map,Integer userId,Integer actionId,String actionPath){
    	map.put("userId", userId);
    	map.put("actionId", actionId);
    	
    	//if判断是什么操作
    	if("lookAllPosition".equals(actionPath) || "addUser".equals(actionPath)){
    		List<Position> positionList = powerService.lookAllPosition();
    		map.put("positionList", positionList);
    	}else if("lookAllUsers".equals(actionPath) ||"updateUserPower".equals(actionPath)){
    		powerService.lookAllUsers(map);
    	}else if("updatePositionPower".equals(actionPath)){
    		List<Position> positionList = powerService.queryPowerForEveryPosition();
    		map.put("positionList", positionList);
    	}
    	
    
    	
    	return "power/"+actionPath;
    }
    
    @RequestMapping("showAllPowerToUserForUpdate.do")
    public String showAllPowerToUserForUpdate(HttpServletRequest request,ModelMap map,Integer execUserId,Integer userId,Integer actionId,String userName) throws Exception{
    	log.info("showAllPowerToUserForUpdate --  userName："+userName);

    	powerService.showAllPowerToUserForUpdate(map,execUserId);
    	map.put("userId", userId);
    	map.put("actionId", actionId);
    	map.put("execUserId", execUserId);//被执行者的userId
    	map.put("userName", userName);
    	return "power/showAllPowerToUserForUpdate";
    }
 
    @RequestMapping("powerAddAction.do")
    @ResponseBody
    public void powerAddAction(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,int userId,int actionId,String action,String actionPath){
    	log.info("userId："+userId+" actionId:"+actionId+" action:"+action+" actionPath:"+actionPath);
    	Boolean b = powerService.powerAddAction(userId,actionId,action,actionPath);
    	if(b){
    		writer.print("success,Please refresh the page!");
    	}else
    		writer.print("您没有操作权限");
    	
    }
    @RequestMapping("powerAddPosition.do")
    @ResponseBody
    public void powerAddPosition(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,int userId,int actionId,String position){
    	log.info("powerAddPosition() -- userId："+userId+" actionId:"+actionId+" position:"+position);
    	Boolean b = powerService.powerAddPosition(userId,actionId,position);
    	if(b){
    		writer.print("success,Please Lookup 查看所有的职位!");
    	}else
    		writer.print("您没有操作权限");    	
    }
    @RequestMapping("addUser.do")
    @ResponseBody
    public void addUser(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,int userId,int actionId,String username,String sex,String age,String password,Integer positionId,Boolean isFlag){
    	log.info("addUser --  userId："+userId+" actionId:"+actionId+" username:"+username+" positionId:"+positionId+" sex:"+sex+" age:"+age+" password:"+password+" isFlag:"+isFlag);
    	Boolean b = powerService.addUser(userId,actionId,username ,sex, age, password, positionId,isFlag);
    	if(b){
    		writer.print("success,Please Lookup 查看所有的职员!");
    	}else
    		writer.print("您没有操作权限");    	
    }
    @RequestMapping("updateUserPower.do")
    @ResponseBody
    public void updateUserPower(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,int userId,int actionId,int execUserId,Integer[] actionIds){
    	log.info("updateUserPower() --  userId："+userId+" actionId:"+actionId+" actionIds:"+JSON.toJSONString(actionIds));
    	Boolean b = powerService.updateUserPower(userId,actionId,execUserId,actionIds);
    	if(b){
    		writer.print("success,Please Lookup 查看所有的职员!");
    	}else
    		writer.print("您没有操作权限");    	
    }
    
    @RequestMapping("showAllPowerToPositionForUpdate.do")
    public String showAllPowerToPositionForUpdate(HttpServletRequest request,ModelMap map,Integer userId,Integer actionId,Integer positionId,String position) throws Exception{
    	log.info("showAllPowerToPositionForUpdate --  position："+position);

    	powerService.showAllPowerToPositionForUpdate(map,positionId);
    	map.put("userId", userId);
    	map.put("actionId", actionId);
    	map.put("positionId", positionId);
    	map.put("position", position);
    	return "power/showAllPowerToPositionForUpdate";
    }
    @RequestMapping("updatePositionPower.do")
    @ResponseBody
    public void updatePositionPower(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,int userId,int actionId,int positionId,Integer[] actionIds){
    	log.info("updateUserPower() --  userId："+userId+" actionId:"+actionId+" actionIds:"+JSON.toJSONString(actionIds));
    	Boolean b = powerService.updatePositionPower(userId,actionId,positionId,actionIds);
    	if(b){
    		writer.print("success,Please Lookup 查看所有的职位!");
    	}else
    		writer.print("您没有操作权限");    	
    }
}
