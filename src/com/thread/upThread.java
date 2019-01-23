package com.thread;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import com.alibaba.fastjson.JSON;
import com.dao.inter.ISuperDao;
import com.entity.AppUsers;
import com.util.DateUtil;

/**
 * @author Hanley 
 * 2018年11月21日下午1:19:56
 *
 */
public class upThread extends Thread{
	
	private static final Logger log=Logger.getLogger(upThread.class);
	
	private int id;
	
	ISuperDao superDao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ISuperDao.class);
	
	public upThread(int id) {
		super();
		this.id = id;
	}


	@Override
	public void run() {
		/*String hql="update AppUsers set vCFP = true where delFlag = false and id = "+id;
		Boolean b = superDao.updateObjectByHql(hql);
		log.info("b:"+b);*/
		
		try{
			/*String hql="from AppUsers where delFlag = false and id = "+id;
	    	Object o = superDao.getObjectByHql(hql);
			log.info("o:"+JSON.toJSONString(o));*/
			System.out.println("id:"+id);
			int i =11;
			AppUsers u = new AppUsers();
    		u.setUserName("张三"+i);
    		u.setPwd(i+123455+"");
    		u.setCreateTime(DateUtil.getCurrentDateByString());
    		u.setDelFlag(false);
    		u.setUserPhone(i+1234567890+"");
        	Serializable id = superDao.addObject(u);
        	if(id!=null){
        		log.info("添加  id:"+i+" 新增 成功！");
        	}else{
        		log.info("新增失败 i:"+i);
        	}
		}catch(Exception e ){
			e.getMessage();
			log.error(e,e);
		}
	}

}
