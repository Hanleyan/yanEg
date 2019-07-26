package com.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.power.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.dao.inter.ISuperDao;
import com.util.DateUtil;

/**
 * @author hanley
 * @date 2018/12/13 15:22
 * 风萧萧兮易水寒
 */
@Service("PowerService")
@Transactional
public class PowerService {
    private static final Log log = LogFactory.getLog(PowerService.class);

    @Autowired
    ISuperDao superDao;

    public Users login(ModelMap m ,String username,String pwd){
    	String hql = "";
    	hql="from Users where 1=1 and delFlag = false and username='"+username+"' and password='"+pwd+"'";
    	Users u = (Users)superDao.getObjectByHql(hql);
    
    	return u;
    	
    
    }
    public void entryPowerIndex(ModelMap m  ,String username,String pwd){
    	//Map<String,Object> m = new HashMap<String,Object>();
    	String hql = "";
    	hql="from Users where 1=1 and delFlag = false and username='"+username+"' and password='"+pwd+"'";
    	Users u = (Users)superDao.getObjectByHql(hql);
    	if(u!=null){
    		//查职位id
    		int positionId = queryUserPositionByUserId(u.getId());
    		//查职位
    		Position p = queryPositionByPositionId( positionId);
    		m.put("position",p);
    		//权限
    		List<Action> actionList= queryUserActionListByUserId(u.getId());
    		
    		//权限分类
    		List<ActionType> actionTypeList = queryActionTypeList();
    		//定义空的一级分类List
    		List<ActionType> delList = new ArrayList<ActionType>();
    		//for (ActionType actionType : actionTypeList) {
    		for (int i = 0;i < actionTypeList.size();i++) {
    			ActionType actionType = actionTypeList.get(i);
    			
				List<Action> acList = new ArrayList<Action>();
				for (Action action : actionList) {
					if(actionType.getId().equals(action.getActionTypeId())){
						acList.add(action);
					}
				}
				actionType.setActionList(acList);
				
				if(acList.size() == 0){
					delList.add(actionType);
				}
			}
    		
    		//移除空的一级分类
    		actionTypeList.removeAll(delList);
    		
    		m.put("actionTypeList", actionTypeList);
    		m.put("actionList", actionList);
    		m.put("user",u);
    		m.put("actionListSize", actionList.size());
    		log.info("username:"+username+" 他的权限："+JSON.toJSONString(actionList));
    		
    	}
    	//json.setResult(m);
    
    }
    
    /***************************用户操作*******************************/
    
    /*************************  各种查询方法     *******************************************************/
    /**
     * 获取权限分类List
     */
    @SuppressWarnings("unchecked")
    public List<ActionType> queryActionTypeList(){
		String hql="from ActionType where 1=1 and delFlag = false ";
		List<ActionType> atList = (List<ActionType>)superDao.getObjectList(hql);
		return atList; 
    }
	/**
	 * 获取商品类型List
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsType> queryGoodsTypeList(){
		String hql="from GoodsType where 1=1 and delFlag = false ";
		List<GoodsType> goodsTypeList = (List<GoodsType>)superDao.getObjectList(hql);
		return goodsTypeList;
	}
	
	/**
	 * 获取商品List
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsInfo> goodsListByGoodsTypeId(int goodsTypeId){
		String hql="from GoodsInfo where 1=1 and delFlag = false and goodsTypeId="+goodsTypeId;
		return (List<GoodsInfo>)superDao.getObjectList(hql);
		
	}
    /**
     * 用户userId查 职位positionId
     */
    public int queryUserPositionByUserId(int userId){
    	//看看ta是什么职位
		String hql="from UserWithPositionInfo where 1=1 and delFlag = false and userId="+userId;
		UserWithPositionInfo uPositionInfo = (UserWithPositionInfo)superDao.getObjectByHql(hql);
		if(uPositionInfo !=null){
			return uPositionInfo.getPositionId();
		}
		return 0;   //查无职位
    }
    /**
     * 用户userId查他的所有权限关系表UserWithActionInfo  actionId
     */
    public List<UserWithActionInfo> queryUserWithActionInfoListByUserId(int userId){
    	String hql="from UserWithActionInfo where 1=1 and delFlag = false and userId="+userId;
		@SuppressWarnings("unchecked")
		List<UserWithActionInfo> uActionInfoList = (List<UserWithActionInfo>)superDao.getObjectList(hql);
		if(uActionInfoList != null){
			return uActionInfoList;
		}else{
			return null;
		}
    	
    }
    /**
     * 用户userId查他的所有权限action
     */
    public List<Action> queryUserActionListByUserId(int userId){
    	List<Action> aList = new ArrayList<Action>();
    	//查他的所有权限关系
    	List<UserWithActionInfo> uActionInfoList=queryUserWithActionInfoListByUserId(userId);
		for (UserWithActionInfo userWithActionInfo : uActionInfoList) {
			Action action = queryActionByActionId(userWithActionInfo.getActionId());
			aList.add(action);
		}
		return aList;
    }
    /**
     * 用户positionId查position
     */
    public Position queryPositionByPositionId(int positionId){
    	//看看ta是什么职位
		String hql="from Position where 1=1 and delFlag = false and id="+positionId;
		Position position = (Position)superDao.getObjectByHql(hql);
		if(position !=null){
			return position;
		}
		return null;   //查无职位
    }
    /**
     * positionId查所有职位与权限关系表PositionWithActionInfo  actionId
     */
    public List<PositionWithActionInfo> queryPositionWithActionInfoListByPositionId(int positionId){
    	String hql="from PositionWithActionInfo where 1=1 and delFlag = false and positionId="+positionId;
		@SuppressWarnings("unchecked")
		List<PositionWithActionInfo> uActionInfoList = (List<PositionWithActionInfo>)superDao.getObjectList(hql);
		if(uActionInfoList != null){
			return uActionInfoList;
		}else{
			return null;
		}
    }
    /**
     * 用户positionId查职位的所有权限action
     */
    public List<Action> queryPositionActionListByPositionId(int positionId){
    	List<Action> aList = new ArrayList<Action>();
    	//查他的所有权限关系
    	List<PositionWithActionInfo> uActionInfoList=queryPositionWithActionInfoListByPositionId(positionId);
		for (PositionWithActionInfo positionWithActionInfo : uActionInfoList) {
			Action action = queryActionByActionId(positionWithActionInfo.getActionId());
			aList.add(action);
		}
		return aList;
    }
    /**
     * 用户actionId查action
     */
    public Action queryActionByActionId(int actionId){
    	//权限id查权限
		String hql="from Action where 1=1 and delFlag = false and id="+actionId;
		Action action = (Action)superDao.getObjectByHql(hql);
		if(action != null){
			return action;
		}
		return null;   //查无权限
    }
    /**
     * 查看所有的职位
     */
    public List<Position> lookAllPosition(){
    	String hql="from Position where 1=1 and delFlag = false";
    	@SuppressWarnings("unchecked")
		List<Position> positionList = (List<Position>)superDao.getObjectList(hql);
		return positionList;
    }
    /**
     * 查每个职位的权限
     */
    public List<Position> queryPowerForEveryPosition(){
    	List<Position> plist = lookAllPosition();
    	for (Position position : plist) {
    		List<PositionWithActionInfo> actList = queryPositionWithActionInfoListByPositionId(position.getId());
			//职位拥有的权限数量
    		position.setActionNum(actList.size());
		}
    	return plist;
    }
    
    /**
     * 查看所有的用户
     */
    public void lookAllUsers(ModelMap m ){
    	String hql="from Users where 1=1 and delFlag = false";
    	@SuppressWarnings("unchecked")
		List<Users> userList = (List<Users>)superDao.getObjectList(hql);
		if(userList != null){
			for (Users users : userList) {
				List<Action> actList = queryUserActionListByUserId(users.getId());
				//用户权限数量
				users.setActionNum(actList.size());
				//用户身份
				if(queryUserPositionByUserId(users.getId()) != 0){
					users.setPosition(queryPositionByPositionId(queryUserPositionByUserId(users.getId())).getPosition());
				}else{
					users.setPosition("暂无职位");
				}
			}
			m.put("userList", userList);
		}
    }
    /**
     * 查所有权限
     */
    public List<Action> showAllPower(){
    	String hql="from Action where 1=1 and delFlag = false";
    	@SuppressWarnings("unchecked")
		List<Action> allActionList = (List<Action>)superDao.getObjectList(hql);
    	return allActionList;
    }
    
    /**
     * 查看所有职位及职位的权限
     */
    public void showAllPowerToPositionForUpdate(ModelMap m ,Integer positionId){
    	
    	List<Action> allActionList = showAllPower();
    	m.put("allActionList", allActionList);//所有权限
    	
    	List<Action> positionActList = queryPositionActionListByPositionId(positionId);
		m.put("positionActList", positionActList);  //所有职位对应的权限
		m.put("positionActListSize", positionActList.size());//所有职位对应的权限数量
		
    }
    /**
     * 查看所有权限及用户的权限
     */
    public void showAllPowerToUserForUpdate(ModelMap m ,Integer userId){
    	List<Action> allActionList = showAllPower();
    	m.put("allActionList", allActionList);//所有权限
    	
		List<Action> userActList = queryUserActionListByUserId(userId);
		m.put("userActList", userActList);
		m.put("userActListSize", userActList.size());
		
    }
    /*************************  各种查询方法    end *********************************************************/
    
    /*************************  各种添加方法    start *******************************************************/
    /**
     * 添加权限
     */
    public Boolean powerAddAction(int userId,int actionId,String action,String actionPath,int actionTypeId){
        Boolean flag = false;
        //判断action是否为空
        if(StringUtils.isEmpty(action)){
        	return flag;
        }
        //判断actionPath是否为空
        if(StringUtils.isEmpty(actionPath)){
        	return flag;
        }
		//判断actionTypeId是否为空
		if(StringUtils.isEmpty(String.valueOf(actionTypeId))){
			return flag;
		}
        //查看此人有没有操作这个的权限
        Boolean bool = isPowerToUserWithAction(userId,actionId);
        if(bool){
        	//添加权限
        	Action act = new Action();
        	act.setAction(action);
        	act.setActionPath(actionPath);
        	act.setCreateTime(new Date());
        	act.setDelFlag(false);
        	act.setActionTypeId(actionTypeId);
        	Serializable id = superDao.addObject(act);
        	if(id != null){
        		//添加用户与权限的关系
        		addUserWithActionInfo(Integer.parseInt(id.toString()),userId);
        	}
        	flag = true;
        }       
        return flag;
    }  
    /**
     * 添加职位
     */
    public Boolean powerAddPosition(int userId,int actionId,String position){
        Boolean flag = false;
        //查看此人有没有操作这个的权限
        Boolean bool = isPowerToUserWithAction(userId,actionId);
        if(bool){
        	//添加职位
        	Position po = new Position();
        	po.setCreateTime(new Date());
        	po.setDelFlag(false);
        	po.setPosition(position);
        	Serializable id = superDao.addObject(po);
        	if(id != null){
        		log.info("职位 "+position+" 添加成功");
        	}
        	flag = true;
        }
        return flag;
    }
    
    /**
     * 添加职员
     */
    public Boolean addUser(int userId,int actionId,String username,String sex,String age,String password,Integer positionId,Boolean isFlag){  //isFlag为ture则表示同意把职位的权限赋给用户
        Boolean flag = false;
        //查看此人有没有操作这个的权限
        Boolean bool = isPowerToUserWithAction(userId,actionId);
        if(bool){
        	//添加职员
        	Users u = new Users();
        	u.setCreateTime(new Date());
        	u.setDelFlag(false);
        	u.setAge(age);
        	u.setPassword(password);
        	u.setUsername(username);
        	u.setSex(sex);
        	Serializable id = superDao.addObject(u);
        	if(id != null){
        		log.info("职员 "+username+" 添加成功");
        		//给职员添加职位
        		Boolean b = addUserWithPositionInfo(positionId,Integer.parseInt(id.toString()));
        		if(b){
        			log.info("职员 "+username+" 添加职位编号:"+positionId+" 成功.");
        			if(isFlag){    //添加该职位的所有权限
        				List<PositionWithActionInfo> pwalist =queryPositionWithActionInfoListByPositionId(positionId);
        				for (PositionWithActionInfo positionWithActionInfo : pwalist) {
        					Boolean uwa = addUserWithActionInfo(positionWithActionInfo.getActionId(),Integer.parseInt(id.toString()));
        					if(uwa) log.info("职员 "+username+" 添加权限编号:"+positionWithActionInfo.getActionId()+" 成功");
        					else log.info("职员 "+username+" 添加权限编号:"+positionWithActionInfo.getActionId()+" 失败");
						}
        			}
        		}else{
        			log.info("职员 "+username+" 添加职位编号:"+positionId+" 失败.");
        		}
        		
        	}
        	flag = true;
        }
        return flag;
    }
	/**
	 * 添加商品分类
	 */
	public Boolean addGoodsType(int userId,int actionId,String goodsTypeName){
		Boolean flag = false;
		//判断goodsTypeName是否为空
		if(StringUtils.isBlank(goodsTypeName)){
			return flag;
		}
		//查看此人有没有操作这个的权限
		Boolean bool = isPowerToUserWithAction(userId,actionId);
		if(bool){
			//添加商品分类
			GoodsType entity = new GoodsType();
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			entity.setDelFlag(false);
			entity.setGoodsTypeName(goodsTypeName);
			Serializable id = superDao.addObject(entity);
			if(id != null){
				log.info("商品分类 "+goodsTypeName+" 添加成功");
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * 添加商品
	 */
	public Boolean addGoods(int userId,int actionId,GoodsInfo goodsInfo){
		Boolean flag = false;
		//判断goodsTypeName是否为空
		if(goodsInfo != null && !StringUtils.isBlank(goodsInfo.getGoodsName()) && !StringUtils.isBlank(goodsInfo.getGoodsTypeId().toString())
				&& !StringUtils.isBlank(goodsInfo.getStorage().toString()) && !StringUtils.isBlank(goodsInfo.getGoodsAmout().toString()) && !StringUtils.isBlank(goodsInfo.getGoodsPrice().toString())
				&& !StringUtils.isBlank(goodsInfo.getGoodsVipPrice().toString()) && !StringUtils.isBlank(goodsInfo.getGoodsDiscount().toString()) && !StringUtils.isBlank(goodsInfo.getGoodsDescription())){

			//查看此人有没有操作这个的权限
			Boolean bool = isPowerToUserWithAction(userId,actionId);
			if(bool){
				//添加商品
				goodsInfo.setCreateTime(new Date());
				goodsInfo.setUpdateTime(new Date());
				goodsInfo.setDelFlag(false);
				goodsInfo.setGoodsSaleNum(0);
				goodsInfo.setGoodsLookAmount(0);
				Serializable id = superDao.addObject(goodsInfo);
				if(id != null){
					log.info("商品 "+goodsInfo.getGoodsName()+" 添加成功");
				}
				flag = true;
			}
		}
		return flag;
	}
    /**
     * 添加 用户与权限关系
     */
    public Boolean addUserWithActionInfo(int actionId,int userId){
    	Boolean flag = false;
    	//添加 用户与权限关系
    	UserWithActionInfo userWithActionInfo = new UserWithActionInfo();
    	userWithActionInfo.setActionId(actionId);
    	userWithActionInfo.setUserId(userId);
    	userWithActionInfo.setCreateTime(new Date());
    	userWithActionInfo.setDelFlag(false);
    	Serializable id = superDao.addObject(userWithActionInfo);
    	if(id != null){
    		flag = true;
    	}
    	return flag;
    }
    /**
     * 添加 职位与权限关系
     */
    public Boolean addPositionWithActionInfo(int actionId,int positionId){
    	Boolean flag = false;
    	//添加 用户与权限关系
    	PositionWithActionInfo posiWithActionInfo = new PositionWithActionInfo();
    	posiWithActionInfo.setActionId(actionId);
    	posiWithActionInfo.setPositionId(positionId);
    	posiWithActionInfo.setCreateTime(new Date());
    	posiWithActionInfo.setDelFlag(false);
    	Serializable id = superDao.addObject(posiWithActionInfo);
    	if(id != null){
    		flag = true;
    	}
    	return flag;
    }
    
    /**
     * 添加 用户与职位关系
     */
    public Boolean addUserWithPositionInfo(int positionId ,int userId){
    	Boolean flag = false;
    	//添加 用户与职位关系
    	UserWithPositionInfo  userWithPisitionInfo = new UserWithPositionInfo();
    	userWithPisitionInfo.setCreateTime(new Date());
    	userWithPisitionInfo.setDelFlag(false);
    	userWithPisitionInfo.setPositionId(positionId);
    	userWithPisitionInfo.setUserId(userId);
    	Serializable id = superDao.addObject(userWithPisitionInfo);
    	if(id != null){
    		flag = true;
    	}
    	return flag;
    }

    /*************************  各种添加方法    end *********************************************************/
    
    /**
     * 判断用户有没有此权限
     */
    public Boolean isPowerToUserWithAction(int userId,int actionId){
    	String hql = "from  UserWithActionInfo where 1=1 and delFlag = false and userId="+userId+" and actionId="+actionId;
        UserWithActionInfo uwa = (UserWithActionInfo)superDao.getObjectByHql(hql);
        if(uwa != null) return true;
        else return false;
    }
    
    
    
    /**
     * 修改职员权限
     */
    public Boolean updateUserPower(int userId,int actionId,int execUserId,Integer[] actionIds){
        Boolean flag = false;
        //查看此执行者有没有操作这个的权限
        Boolean bool = isPowerToUserWithAction(userId,actionId);//执行者userId和actionId
        if(bool){
        	//先删除没有赋予的权限   先拿到此人所有的权限id去 跟数组actionIds比对 ，如果actionIds没有那就删除
        	List<UserWithActionInfo> uActionInfoList=queryUserWithActionInfoListByUserId(execUserId);
        
        	for (UserWithActionInfo userWithActionInfo : uActionInfoList) {
        		a:for (int i = 0; i < actionIds.length; i++) {
					log.info(userWithActionInfo.getActionId() +"  "+ actionIds[i]);
					if(userWithActionInfo.getActionId() == actionIds[i]){
						//跳出这一层循环
						System.out.println("我要跳出至上一层循环");
						break a;
					}else if(i == actionIds.length - 1){
						//删除
						Boolean b = updateUserWithActionInfo(userWithActionInfo.getActionId(),execUserId,true);
						if(b) log.info("用户userId="+execUserId+" 删除权限actionId="+userWithActionInfo.getActionId()+" 成功");
						else log.info("用户userId="+execUserId+" 删除权限actionId="+userWithActionInfo.getActionId()+" 失败");
					}
					
				}
	        	
			}
        	
        	//遍历数组 actionIds    如果用户有此权限则不操作，如果用户曾经有此权限则把曾经删除标志修改， 如果用户曾经也没有那么给用户添加新权限
        	for (Integer actId : actionIds) {
				//先看看此用户(execUserId)是否已有此权限(actId)
        		String hql="from UserWithActionInfo where 1=1 and actionId="+actId+" and userId="+execUserId;//delFlag = false
        		UserWithActionInfo uwa  = (UserWithActionInfo)superDao.getObjectByHql(hql);
        		if(uwa != null && uwa.getDelFlag() == true){ //有 但是删掉了 ， 那就改回来delFlag = false
        			Boolean b = updateUserWithActionInfo(actId,execUserId,false);
        			if(b) log.info("用户userId="+execUserId+" 加权限actionId="+actId+" 成功");
        			else log.info("用户userId="+execUserId+" 加权限actionId="+actId+" 失败");
        		}else if(uwa == null){
        			//没有 那就给用户添加权限
        			Boolean boo = addUserWithActionInfo( actId, execUserId);
        			if(boo) log.info("用户userId="+execUserId+" 加权限actionId="+actId+" 成功");
            		else log.info("用户userId="+execUserId+" 加权限actionId="+actId+" 失败");
        		}
        		
			}
        	
        	flag = true;
        }
        return flag;
    }
    
    /**
     * 修改职位权限
     */
    public Boolean updatePositionPower(int userId,int actionId,int positionId,Integer[] actionIds){
        Boolean flag = false;
        //查看此执行者有没有操作这个的权限
        Boolean bool = isPowerToUserWithAction(userId,actionId);//执行者userId和actionId
        if(bool){
        	//先删除没有赋予的权限   先拿到此职位所有的权限id去 跟数组actionIds比对 ，如果actionIds没有那就删除
        	List<PositionWithActionInfo> pActionInfoList=queryPositionWithActionInfoListByPositionId(positionId);
        
        	for (PositionWithActionInfo posiWithActionInfo : pActionInfoList) {
        		a:for (int i = 0; i < actionIds.length; i++) {
					log.info(posiWithActionInfo.getActionId() +"  "+ actionIds[i]);
					if(posiWithActionInfo.getActionId() == actionIds[i]){
						//跳出这一层循环
						System.out.println("我要跳出至上一层循环");
						break a;
					}else if(i == actionIds.length - 1){
						//删除
						Boolean b = updatePositionWithActionInfo(posiWithActionInfo.getActionId(),positionId,true);
						if(b) log.info("职位positionId="+positionId+" 删除权限actionId="+posiWithActionInfo.getActionId()+" 成功");
						else log.info("职位positionId="+positionId+" 删除权限actionId="+posiWithActionInfo.getActionId()+" 失败");
					}
					
				}
	        	
			}
        	
        	//遍历数组 actionIds    如果职位有此权限则不操作，如果职位曾经有此权限则把曾经删除标志修改， 如果职位曾经也没有那么给用户添加新权限
        	for (Integer actId : actionIds) {
				//先看看此用户(execUserId)是否已有此权限(actId)
        		String hql="from PositionWithActionInfo where 1=1 and actionId="+actId+" and positionId="+positionId;//delFlag = false
        		PositionWithActionInfo pwa  = (PositionWithActionInfo)superDao.getObjectByHql(hql);
        		if(pwa != null && pwa.getDelFlag() == true){ //有 但是删掉了 ， 那就改回来delFlag = false
        			Boolean b = updatePositionWithActionInfo(actId,positionId,false);
        			if(b) log.info("用户userId="+positionId+" 加权限actionId="+actId+" 成功");
        			else log.info("用户userId="+positionId+" 加权限actionId="+actId+" 失败");
        		}else if(pwa == null){
        			//没有 那就给用户添加权限
        			Boolean boo = addPositionWithActionInfo( actId, positionId);
        			if(boo) log.info("用户userId="+positionId+" 加权限actionId="+actId+" 成功");
            		else log.info("用户userId="+positionId+" 加权限actionId="+actId+" 失败");
        		}
        		
			}
        	
        	flag = true;
        }
        return flag;
    }
    /**
     * 删除职位权限
     */
    public Boolean updatePositionWithActionInfo(Integer actId,Integer positionId,Boolean boo){  //boo为false则添加   boo为true则删除
    	String hql="update PositionWithActionInfo set updateTime='"+DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss")+"',delFlag = "+boo+" where 1=1 and actionId="+actId+" and positionId="+positionId;
		Boolean b = superDao.updateObjectByHql(hql);
		return b;
    }
    /**
     * 删除职员权限
     */
    public Boolean updateUserWithActionInfo(Integer actId,Integer execUserId,Boolean boo){  //boo为false则添加   boo为true则删除
    	String hql="update UserWithActionInfo set updateTime='"+DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss")+"',delFlag = "+boo+" where 1=1 and actionId="+actId+" and userId="+execUserId;
		Boolean b = superDao.updateObjectByHql(hql);
		return b;
    }
    
}
