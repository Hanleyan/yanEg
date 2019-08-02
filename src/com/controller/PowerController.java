package com.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
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
import com.entity.power.Action;
import com.entity.power.ActionType;
import com.entity.power.Position;
import com.entity.power.PowerPOJO;
import com.entity.power.Users;
import com.entity.power.goods.GoodsInfo;
import com.entity.power.goods.GoodsType;
import com.entity.power.order.Orders;
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
    @ResponseBody
    public String powerLogin(HttpServletRequest request, HttpServletResponse response,ModelMap m , PrintWriter writer,String username,String pwd){  // PrintWriter writer,
    	log.info("username："+username+" pwd:"+pwd);
    	Users u =  powerService.login(m,username,pwd);
        log.info("登入用户u："+JSON.toJSONString(u));
        if(u != null) {
        	setSessionUser(request, u);
        	writer.write("success");
        }else {
        	writer.write("用户名或密码error");
        }
        return null;

    }
    @RequestMapping("powerLoginOut.do")
    public String powerLogin(HttpServletRequest request, HttpServletResponse response){  // PrintWriter writer,
    	
    	Users u =  getSessionUser(request);
        log.info("退出登入用户u："+JSON.toJSONString(u));
        if(u != null) {
        	//使session失效，让用户重新登入
    		request.getSession().invalidate();
    		
    		return "power/powerLogin";
        }
        return "power/powerLogin";

    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("entryPowerIndex.do")
    public String entryPowerIndex(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,ModelMap m){
    	Users u = getSessionUser(request);
    	log.info("username："+u.getUsername()+" pwd:"+u.getPassword());
    	powerService.entryPowerIndex(m ,u.getUsername(),u.getPassword());
    	
    	//用户职位加到session中
    	setSessionUserPosition(request,(Position)m.get("position"));
    	//用户权限加到session中
    	setUserRight(request,(List<Action>)m.get("actionList"));
		//用户权限分类加到session中
		setUserRightType(request,(List<ActionType>)m.get("actionTypeList"));

    	//用户权限数量加到session中
    	setUserRightSize(request,(Integer)m.get("actionListSize"));
    	
    	return "power/powerIndex";
    }
    

    @RequestMapping("entryTakeAction")
    public String entryTakeAction(ModelMap map,Integer userId,Integer actionId,String actionPath){
    	map.put("userId", userId);
    	map.put("actionId", actionId);
    	//获取权限的一级和二级,用做页面的导航栏
    	PowerPOJO power = powerService.getPowerPOJO(actionId);
    	map.put("power", power);
    	
    	//if判断是什么操作
    	if("lookAllPosition".equals(actionPath) || "addUser".equals(actionPath)){
    		List<Position> positionList = powerService.lookAllPosition();
    		map.put("positionList", positionList);
    	}else if("lookAllUsers".equals(actionPath) ||"updateUserPower".equals(actionPath)){
    		List<Users> userList = powerService.lookAllUsers();
    		map.put("userList", userList);
    	}else if("updatePositionPower".equals(actionPath)){
    		List<Position> positionList = powerService.queryPowerForEveryPosition();
    		map.put("positionList", positionList);
    	}else if( "powerType".equals(actionPath) || "addPowerType".equals(actionPath) || "updatePowerType".equals(actionPath)){
    		if("addPowerType".equals(actionPath) || "updatePowerType".equals(actionPath)){
    			actionPath = "powerType";
    		}
			List<ActionType> actionTypeList = powerService.queryActionTypeList();
			map.put("actionTypeList", actionTypeList);
		}else if("addGoods".equals(actionPath) || "goodsTypeList".equals(actionPath) || "goodsList".equals(actionPath)){
			List<GoodsType> goodsTypeList = powerService.queryGoodsTypeList();
			map.put("goodsTypeList", goodsTypeList);
			if("goodsList".equals(actionPath)){
				List<List<GoodsInfo>> goodsArrayList = powerService.getGoodsList(0,null,null);
				map.put("goodsArrayList", goodsArrayList);
				log.info("goodsArrayList:"+JSON.toJSONString(goodsArrayList));
			}
		}else if("addPower".equals(actionPath) || "actionList".equals(actionPath) || "updateAction".equals(actionPath)){
			if("addPower".equals(actionPath) || "updateAction".equals(actionPath)){
				actionPath = "actionList";
			}
			List<Action> actionList = powerService.queryAllActionList();
			map.put("actionList", actionList);
			List<ActionType> actionTypeList = powerService.queryActionTypeList();
			map.put("actionTypeList", actionTypeList);
		}else if("ordersList".equals(actionPath)){
			List<Orders> ordersList = powerService.queryOrdersList(null,null);
			map.put("ordersList", ordersList);
		}
    	

    	
    	return "power/"+actionPath;
    }
    
    @RequestMapping("makeOrder.do")
    @ResponseBody
    public String makeOrder(HttpServletRequest request, Integer[] goodsIds,BigDecimal ordersPrice,Integer orderSource,Integer orderPayType) throws Exception{
    	Users user = getSessionUser(request);
    	log.info("***********************用户"+user.getUsername()+"来下单啦*************************************** \n "
    			+ "makeOrder新增商品订单  goodsIds："+JSON.toJSONString(goodsIds)+" ordersPrice:"+ordersPrice
    			+" orderSource:"+orderSource+" orderPayType:"+orderPayType);
    	JsonContent jsonContent = new JsonContent();
    	
    	String actionPath = "makeOrder";
    	boolean b = powerService.makeOrder(user,actionPath,goodsIds, ordersPrice,orderSource,orderPayType);
    	if(b){
    		jsonContent.setCode(EnumMessageCode.code1.getId());
    	}else{
    		jsonContent.setCode(EnumMessageCode.code4.getId());
    	}
    	jsonContent.setMessage(EnumMessageCode.getDescById(jsonContent.getCode()));
    	
    	log.info("makeOrder新增一条单商品订单返回："+JSON.toJSONString(jsonContent));
		return JSON.toJSONString(jsonContent);
    }
    
    @RequestMapping("goodsListByGoodsTypeId.do")
    @ResponseBody
    public String goodsListByGoodsTypeId(HttpServletRequest request , int goodsTypeId) throws Exception{
    	log.info("goodsTypeId获取商品列表 goodsTypeId："+goodsTypeId);
    	JsonContent jsonContent = new JsonContent();
    	jsonContent.setCode(EnumMessageCode.code1.getId());
    	jsonContent.setMessage(EnumMessageCode.getDescById(jsonContent.getCode()));
    	jsonContent.setResult(powerService.getGoodsList(goodsTypeId,null,null));
    	log.info("goodsListByGoodsTypeId返回："+JSON.toJSONString(jsonContent));
		return JSON.toJSONString(jsonContent);
    }
    
    
    @RequestMapping("showAllPowerToUserForUpdate.do")
    public String showAllPowerToUserForUpdate(HttpServletRequest request,ModelMap map,Integer execUserId,Integer userId,Integer actionId,String userName) throws Exception{
    	log.info("showAllPowerToUserForUpdate --  userName："+userName);

    	powerService.showAllPowerToUserForUpdate(map,execUserId);
    	map.put("userId", userId);//执行者(操作员)userId
    	map.put("actionId", actionId);
    	map.put("execUserId", execUserId);//被执行者的userId
    	map.put("userName", userName);
    	return "power/showAllPowerToUserForUpdate";
    }
   
    @RequestMapping("showUserDetail.do")
    public String showUserDetail(HttpServletRequest request,ModelMap map,Integer execUserId,String userName) throws Exception{
    	log.info("showUserDetail --  userName："+userName);

    	powerService.showAllPowerToUserForUpdate(map,execUserId);
    	map.put("userName", userName);
    	return "power/showUserDetail";
    }
    @RequestMapping("addPowerType.do")
    @ResponseBody
    public void addPowerType(HttpServletRequest request, HttpServletResponse response,PrintWriter writer,String powerTypeName){
    	Users user = getSessionUser(request);
    	JsonContent jsonContent = new JsonContent();
    	log.info("addPowerType新增权限类型 --  userId："+user.getId()+"  新增的权限类型名称powerTypeName:"+powerTypeName);
    	
    	String actionPath="addPowerType";//新增权限类型
    	Boolean b = powerService.addPowerType(user.getId(),actionPath,powerTypeName);
    	if(b){
    		jsonContent.setCode(EnumMessageCode.code1.getId());
    	}else{
    		jsonContent.setCode(EnumMessageCode.code3.getId());
    	}
    	jsonContent.setMessage(EnumMessageCode.getDescById(jsonContent.getCode()));
    	String retStr = JSON.toJSONString(jsonContent);
    	log.info("新增权限类型 返回结果:"+retStr);
    	
    	writer.write(retStr);
    }
    
    @RequestMapping("updateAction.do")
    @ResponseBody
    public void updateAction(HttpServletRequest request, HttpServletResponse response,PrintWriter writer,Integer id,String actionName,String actionEName,Integer actionTypeId){
    	Users user = getSessionUser(request);
    	JsonContent jsonContent = new JsonContent();
    	log.info("updateAction修改权限 --  userId："+user.getId()+" id:"+id+" actionName:"+actionName+" actionEName:"+actionEName+" actionTypeId:"+actionTypeId);
    	
    	String actionPath="updateAction";
    	Boolean b = powerService.updateAction(user.getId(),actionPath,id,actionName,actionEName,actionTypeId);
    	if(b){
    		//使session失效，让用户重新登入
    		request.getSession().invalidate();
    		jsonContent.setCode(EnumMessageCode.code1.getId());
    	}else{
    		jsonContent.setCode(EnumMessageCode.code3.getId());
    	}
    	jsonContent.setMessage(EnumMessageCode.getDescById(jsonContent.getCode()));
    	String retStr = JSON.toJSONString(jsonContent);
    	log.info("新增权限类型 返回结果:"+retStr);
    	
    	writer.write(retStr);
    }
    
    @RequestMapping("updatePowerType.do")
    @ResponseBody
    public void updatePowerType(HttpServletRequest request, HttpServletResponse response,PrintWriter writer,Integer id,String powerTypeName){
    	Users user = getSessionUser(request);
    	JsonContent jsonContent = new JsonContent();
    	log.info("updatePowerType修改权限类型 --  userId："+user.getId()+" id:"+id+" powerTypeName:"+powerTypeName);
    	
    	String actionPath="updatePowerType";//新增权限类型
    	Boolean b = powerService.updatePowerType(user.getId(),actionPath,id,powerTypeName);
    	if(b){
    		jsonContent.setCode(EnumMessageCode.code1.getId());
    	}else{
    		jsonContent.setCode(EnumMessageCode.code3.getId());
    	}
    	jsonContent.setMessage(EnumMessageCode.getDescById(jsonContent.getCode()));
    	String retStr = JSON.toJSONString(jsonContent);
    	log.info("新增权限类型 返回结果:"+retStr);
    	
    	writer.write(retStr);
    }
    
    @RequestMapping("powerAddAction.do")
    @ResponseBody
    public void powerAddAction(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,String action,String actionPath,int actionTypeId){ //int userId,int actionId,
    	Users user = getSessionUser(request);
    	log.info("添加权限userId："+user.getId()+" action:"+action+" actionPath:"+actionPath);
    	Boolean b = powerService.powerAddAction(user.getId(),action,actionPath,actionTypeId);
    	if(b){
    		writer.print("success,Please refresh the page!");

			//使session失效，让用户重新登入
    		request.getSession().invalidate();
    	}else
    		writer.print("您没有操作权限或输入项为空");
    	
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
	@RequestMapping("addGoodsType.do")
	@ResponseBody
	public void addGoodsType(HttpServletRequest request, PrintWriter writer,int actionId,String goodsTypeName){
    	Users users = getSessionUser(request);
		log.info("addGoodsType --  操作员："+users.getUsername()+" 正在添加商品分类 actionId:"+actionId+" goodsTypeName:"+goodsTypeName);
		Boolean b = powerService.addGoodsType(users.getId(),actionId,goodsTypeName);
		if(b){
			writer.print("success!");
		}else
			writer.print("您没有操作权限或输入项为空");
	}
	@RequestMapping("addGoods.do")
	@ResponseBody
	public void addGoods(HttpServletRequest request, PrintWriter writer, String goodsName, int goodsTypeId, int storage, int goodsAmout, BigDecimal goodsPrice,BigDecimal goodsVipPrice,
						 BigDecimal goodsDiscount,String goodsDescription,int actionId){
		Users users = getSessionUser(request);
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsName(goodsName);
		goodsInfo.setGoodsTypeId(goodsTypeId);
		goodsInfo.setStorage(storage);
		goodsInfo.setGoodsAmout(goodsAmout);
		goodsInfo.setGoodsPrice(goodsPrice);
		goodsInfo.setGoodsVipPrice(goodsVipPrice);
		goodsInfo.setGoodsDiscount(goodsDiscount);
		goodsInfo.setGoodsDescription(goodsDescription);
		log.info("addGoods --  操作员："+users.getUsername()+" 正在添加商品 actionId:"+actionId+"goodsInfo:"+JSON.toJSONString(goodsInfo));

		Boolean b = powerService.addGoods(users.getId(),actionId,goodsInfo);
		if(b){
			writer.print("success!");
		}else
			writer.print("您没有操作权限或输入项为空");
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
