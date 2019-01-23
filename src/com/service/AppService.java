package com.service;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cilent.CyhClient;
import com.dao.inter.ISuperDao;
import com.entity.AppUsers;
import com.entity.Customer;
import com.entity.HanziIndex;
import com.thread.upThread;
import com.util.DateUtil;
import com.util.JsonContent;
import com.util.Tool;

@SuppressWarnings("unused")
@Service("AppService")
//事物注解可以用于接口定义、接口方法、类定义、类公开方法
@Transactional(readOnly = false,propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
//Propagation:事物的传播
//readOnly:事物读写性
//rollbackFor:回滚{}，多个事物用逗号分开
public class AppService {
    //service工厂类调用每个业务的地方

    @Autowired
    ISuperDao superDao;

    private static final Logger log = Logger.getLogger(AppService.class);

    /**
     * 添加Customer
     * @param json
     * @return
     */
    public Boolean addCustomer(JsonContent json){
    	try{
    		for (long i = 5100002; i <=5100002+ 200*10000; i++) { 
        		Customer c = new Customer();
            	Date d = new Date();
            	c.setCreateDate(d);
            	c.setDelFlag(false);
            	c.setCustomerName("hanley"+i);
            	c.setCustomerAddress("上海"+i);
            	c.setCustomerMail("www."+c.getCustomerName()+c.getCustomerAddress()+"-"+i);
            	c.setCustomerPhone(i+"");
            	Serializable id = superDao.addObject(c);
            	if(id!=null){
            		log.info("id:"+i+" 新增 成功！");
            	}else{
            		log.info("新增失败");
            		return false;
            	}
    		}
    	}catch(Exception e){
    		e.getMessage();
    		log.error(e,e);
    	}
    	return true;
    }
   /**
    * 查询 queryCustomer
    * @param map
    * @param json
    * @param customerName
    * @param customerMail
    * @param customerPhone
    * @param customerAddress
    * @return
    */
    public Boolean queryCustomer(ModelMap map,JsonContent json,String customerName,String customerMail,String customerPhone,String customerAddress){
    	
        String hql="from Customer where 1=1 and delFlag=false";
        String wherehql = " and customerName like '%"+Tool.isNull(customerName)+"%' and customerMail like '%"+Tool.isNull(customerMail)+"%' and customerPhone like '%"+Tool.isNull(customerPhone)+"%' and customerAddress like '%"+Tool.isNull(customerAddress)+"%'";
        
        String sql="SELECT COUNT(id) from customer";
        Integer  t = (Integer )superDao.getCount(sql);
		
        @SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>)superDao.getObjectList(hql+wherehql);
        if(list.size()>0){
        	if(json != null){
        		json.setResult(list);
        	}
            if(map != null){
            	map.put("CustomerList", JSON.toJSON(list));
            	map.put("ListSize", t);
            }
        }
		return true;
    }
    
	public Boolean queryCustomerSize(JsonContent json) {

		String sql = " SELECT COUNT(id) from customer where 1=1 and delFlag=false";

		Integer t = (Integer) superDao.getCount(sql);

		if (t != null) {
			json.setResult("Customer数量："+t);
		}
		return true;
	}
	
	public Boolean queryCustomerByLucene(ModelMap map,JsonContent json,String customerName,String customerMail,String customerPhone,String customerAddress){
    	
        String hql="from Customer where 1=1 and delFlag=false";
        String wherehql = " and customerName like '%"+Tool.isNull(customerName)+"%' and customerMail like '%"+Tool.isNull(customerMail)+"%' and customerPhone like '%"+Tool.isNull(customerPhone)+"%' and customerAddress like '%"+Tool.isNull(customerAddress)+"%'";
        
        String sql="SELECT COUNT(id) from customer";
        Integer  t = (Integer )superDao.getCount(sql);
		
        @SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>)superDao.getObjectList(hql+wherehql);
        if(list.size()>0){
        	if(json != null){
        		json.setResult(list);
        	}
            if(map != null){
            	map.put("CustomerList", JSON.toJSON(list));
            	map.put("ListSize", t);
            }
        }
		return true;
    }
    /**
     * 上传文件   ------- 这只是个例子
     * @param map
     * @param newFile
     */
    @SuppressWarnings("rawtypes")
	public Boolean addFile(HttpServletRequest req, ModelMap map ,String newFile){
    	try{
    		MultipartResolver resolver = new CommonsMultipartResolver(req.getSession().getServletContext());
        	if(resolver != null){
        		MultipartHttpServletRequest mRequest =  resolver.resolveMultipart(req);
        		if(mRequest != null){
        			Map<String,MultipartFile> fileMap =  mRequest.getFileMap();
        			MultipartFile[] files = new MultipartFile[fileMap.size()];
        			if(files != null && files.length>0){
        				for (Map.Entry entity  : fileMap.entrySet()) {
    						MultipartFile file =  (MultipartFile) entity.getValue();
    						
    						String fileName = file.getOriginalFilename();
    						/*String path1 = Thread.currentThread()
    								.getContextClassLoader().getResource("").getPath()
    								+ "download" + File.separator;*/
    						String path1 = "D:/testFile/";
    						// 下面的加的日期是为了防止上传的名字一样
    						String path = path1
    								+ new SimpleDateFormat("yyyyMMddHHmmss")
    										.format(new Date()) +"-"+ fileName;
    						File localFile = new File(path);
    						file.transferTo(localFile);
    						
    						System.out.println(file.getContentType()+"-"+file.getName()+"-"+file.getSize());
    						
    						//savePic(file.getInputStream(), file.getOriginalFilename());
    					}
        			}
        			
        		}
     
        	}
    	}catch(Exception e){
    		e.getStackTrace();
    		log.error(e.getMessage());
    		log.error(e,e);
    	}
		return true;
    }
    
    /**
     * 添加汉字表
     * @param json
     * @return
     */
    public Boolean addHanzi(JsonContent json){
    	DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
    	try{
    		for (int i = 1; i < 10000; i++) { 
        		HanziIndex h = new HanziIndex();
        		h.setHanzi(Tool.getTenHanziByRandom());
        		h.setDelFlag("0");
        		h.setCreateDate(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            	Serializable id = superDao.addObject(h);
            	if(id!=null){
            		log.info("添加汉字  id:"+i+" 新增 成功！");
            	}else{
            		log.info("新增失败");
            		return false;
            	}
    		}
    	}catch(Exception e){
    		e.getMessage();
    		log.error(e,e);
    	}
    	return true;
    }
    
    /**
     * 添加AppUsers
     * @param json
     * @return
     */
	public Boolean addAppUsers(JsonContent json) {
		try {
			
			//开线程池
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			System.out.println("多线程 start"
					+ Thread.currentThread().getName());
			cachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					long start=System.currentTimeMillis();
					for (int i = 12; i <= 50000; i++) {
						final int index = i;

						AppUsers u = new AppUsers();
						u.setUserName("张三" + i);
						u.setPwd(i + 123455 + "");
						u.setCreateTime(new Date());
						u.setDelFlag(false);
						u.setUserPhone(i + 1234567890 + "");
						Serializable id = superDao.addObject(u);
						if (id != null) {
							log.info("添加  id:" + i + " 新增 成功！");
						} else {
							log.info("新增失败 i:" + i);
						}
					}
					long end=System.currentTimeMillis();
					System.out.println("多线程耗时："+(end-start)+"  ms");
				}
			});

			System.out.println("多线程 over");
		} catch (Exception e) {
			e.getMessage();
			log.error(e, e);
		}
		return true;
	}
    
    
    public Boolean upAppUsers(JsonContent json,final int id){
    	try{
    		
    		/*********---开启线程池创建多线程---*********/
    		ExecutorService cachedThreadPool = Executors.newSingleThreadExecutor();
    		System.out.println("线程upAppUsers() start -- "+Thread.currentThread().getName());
    		cachedThreadPool.execute(new Runnable() {

		        @Override
		        public void run() {
		        	String hql="update AppUsers set vCFP = true where delFlag = false and id = "+id;
		    		Boolean b = superDao.updateObjectByHql(hql);
		        	
		    		log.info("b:"+b);
		        }
		    });
    		
    		System.out.println("ThreadPool.createThreadPool() over");
    	}catch(Exception e){
    		e.getMessage();
    		log.error(e,e);
    	}
    	return true;
    }
    
    public Boolean upAppUsersByThread(JsonContent json,int id){
    	try{
    		System.out.println("线程upAppUsers() start -- "+Thread.currentThread().getName());
    		
    		/*********---实现Runable的多线程---*********/
    		upThread t = new upThread(id);
    		t.start();
    		
    		/*********---继承Thread的多线程---*********/
    		/*new Thread(new upThread(id)).start();*/
    		System.out.println("线程upAppUsers() over");
    	}catch(Exception e){
    		e.getMessage();
    		log.error(e,e);
    	}
    	return true;
    }
    
    //------------------车易行测试 start------------------------------
    
    public Boolean getDetaliByLicenseNo(JsonContent json,String licenseNo) {

		String str = CyhClient.getDetaliByLicenseNo(licenseNo);
		
		JSONObject jsonContent = (JSONObject)JSONObject.parse(str);

		System.out.println("得到："+jsonContent);
		log.info(jsonContent);
		return true;
	}
    
    public Boolean getScoreByFileNoAndIdNo(JsonContent json,String fileNo,String idNo) {

		String str = CyhClient.getScoreByFileNoAndIdNo(fileNo,idNo);
		
		JSONObject jsonContent = (JSONObject)JSONObject.parse(str);

		System.out.println("得到："+jsonContent);
		log.info(jsonContent);
		return true;
	}
    
    
    public Boolean getDetailByCarNumberAndCarCodeAndCarType(JsonContent json,String carNumber,String carCode,String carType) {
		String str = CyhClient.getDetailByCarNumberAndCarCodeAndCarType("粤S7A3W9","LHGGJ764XH2051266","02");
		JSONObject jsonContent = (JSONObject)JSONObject.parse(str);

		System.out.println("得到："+jsonContent);
		log.info(jsonContent);
		return true;
	}
    
    public Boolean queryTrafficViolation(JsonContent json,String carnumber,String carcode,String cardrivenumber){
    	
    	//String str = CyhClient.queryTrafficViolation(carnumber, carcode, cardrivenumber);
    	//("粤NGG007", "UTF-8")+"&carcode=194336&cardrivenumber=1111";
    	String str = CyhClient.queryTrafficViolation("粤NGG007", "194336","1111");
		
    	
		JSONObject jsonContent = (JSONObject)JSONObject.parse(str);

		System.out.println("得到："+jsonContent);
		log.info(jsonContent);
		return true;
    }
    
    public Boolean queryGateway(JsonContent json,String method_type,String  orderId ,	String third_order_id ,	String order_amount){
    	
    	String str = CyhClient.queryGateway(method_type,orderId, third_order_id, order_amount);
		
		JSONObject jsonContent = (JSONObject)JSONObject.parse(str);

		System.out.println("得到："+jsonContent);
		log.info(jsonContent);
		return true;
    }
    
    
    public Boolean getEffectiveQuerySumByDay(JsonContent json,String date){
    	
    	String str = CyhClient.getEffectiveQuerySumByDay(date);
		
		JSONObject jsonContent = (JSONObject)JSONObject.parse(str);

		System.out.println("得到："+jsonContent);
		log.info(jsonContent);
		return true;
    }
    
  //------------------车易行测试 end------------------------------
}