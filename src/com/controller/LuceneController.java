package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.index.IndexWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.DataConnection;
import com.entity.Customer;
import com.entity.HanziIndex;
import com.util.JsonContent;
import com.util.Tool;
import com.util.luceneUtil.ConnectionPool;
import com.util.luceneUtil.CreateLuceneUtil;
import com.util.luceneUtil.DBManager;
import com.util.luceneUtil.IndexUtil;
import com.util.luceneUtil.SearchLuceneUtil;

@Controller
public class LuceneController {
	private static final Log log = LogFactory.getLog(LuceneController.class);
	
	/**
	 * 创建索引
	 * @param request
	 * @param response
	 * @param writer
	 * @throws IOException 
	 */
	@RequestMapping("/lucene/createLuceneIndex.do")
	public void createLuceneIndex(HttpServletRequest request,HttpServletResponse response,PrintWriter writer) throws IOException{
		long startTime = System.currentTimeMillis();
		
		Tool.getReportIndexPath();//获取创建索引路径
        IndexWriter indexWriter = CreateLuceneUtil.getIndexWriter();//获取索引器
        CreateLuceneUtil.deleteAllIndex();//每次更新之前先清理/
        
        try{
        	//分页的理论
            int everyPage = 1000;//每次查询NW条数据 （每页显示数量）
            //int everyPage = 10;//每次查询NW条数据 （每页显示数量）
            int totalCount;//共多少条（总记录数）
            int totalPage;//分count次查询(总页数)
            int beginIndex = 0;//起始点

            DataConnection data = new DataConnection();
            totalCount =  data.getIndexCount();
            
	          totalPage = IndexUtil.getTotalPage(everyPage, totalCount);
	          ConnectionPool.PooledConnection conn =  DBManager.getConnection();
	          for(int i=1;i<=totalPage;i++){
	              beginIndex = IndexUtil.getBeginIndex(everyPage, i);
	              System.out.println("cusromer 第"+i+"个查询起点"+beginIndex);
	              data.reportIndexBiz(indexWriter, conn, beginIndex, everyPage);
	          }
	          conn.close();
	          indexWriter.commit();//不需要手动提交，自动提交就行
        }catch(Exception ex){
        	 ex.printStackTrace();
        }finally{
        	indexWriter.close();
        }
		
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("创建汉字表索引耗费时间： "+(endTime-startTime)+"ms");
        log.info("创建汉字表索引耗费时间： "+(endTime-startTime)+"ms");
        PrintWriter out = response.getWriter();
        String text = JSONObject.toJSONString("创建汉字表索引耗费时间： "+(endTime-startTime)+"ms");
        System.out.println(text);
        out.print(text);
        out.flush();
        out.close();
	}
	
	/**
	 * Lucene索引搜索
	 * @param request
	 * @param response
	 * @param writer
	 * @param maps
	 * @param pageNo
	 * @param pageSize
	 * @param searchValue
	 * @return
	 */
	@RequestMapping("/lucene/searchLuceneIndex.do")
    private String searchIndex(HttpServletRequest request, HttpServletResponse response, PrintWriter writer, ModelMap maps, Integer pageNo, Integer pageSize, String searchValue) {
        long searchStarts = System.currentTimeMillis();
        searchValue =Tool.ios8859ByUtf8(searchValue);
        System.out.println("searchValue=="+searchValue);
        pageNo = pageNo == null || pageNo == 0 ? 1 : pageNo;
        pageSize = pageSize == null || pageSize.intValue() == 0 ? 15: pageSize;
        Tool tool = new Tool();
        JsonContent json = new JsonContent();
        Tool.getReportIndexPath();//获取创建索引路径
        SearchLuceneUtil luceneUtil = new SearchLuceneUtil();
        Boolean Bool = false;//是否搜索
        Boolean codeBool = false;//是否搜索3ACN 统一征信代码 组织结构代码 注册号
        List<HanziIndex> list=null;
        try {
            list= new ArrayList<HanziIndex>();
           
            String str = searchValue;
            str = str.trim();
            //判断输入参数是否为空
            if(!StringUtils.isEmpty(str)){
                //判断参数是否包含数字或者是纯数字
                if(Tool.getNumbers(str)>5){
                    //包含5位以上数字或纯数字，搜索3ACN 统一征信代码 组织结构代码 注册号
                    System.out.println(str+"----搜索customer");
                    /*codeBool = true;
                    list =  SearchLuceneUtil.getIndexListByCodeList(str,pageNo,pageSize);*/
                }else{
                    //输入参数为中文或者英文，搜索公司名称和法人名称
                    String resultKey = Tool.FilterString(str);
                    if (!resultKey.matches("[a-zA-Z]+") || !resultKey.matches(".*\\d.*")) {
                        //输入参数为中文 并且长度小于等于5  同时搜索法人名称
                        //if(str.length()<=5){
                            Bool = true;
                        //}

                    }
                }
            	
            	Bool = true;
            	
                //多线程搜索开始
                System.out.println("----程序开始运行----");
                Date date1 = new Date();
                int taskSize = 1;
                if(Bool){
                    taskSize = 1;
                }
                // 创建一个线程池
                ExecutorService pool = Executors.newFixedThreadPool(taskSize);
                List<Future> FutureList = new ArrayList<Future>();
                for (int i = 0; i < taskSize; i++) {
                    Callable c = new searchLegelNameThread(str,"",Bool,codeBool,pageSize,pageNo,i);
                    // 执行任务并获取Future对象
                    Future f = pool.submit(c);
                    // System.out.println(">>>" + f.get().toString());
                    FutureList.add(f);
                }
                // 关闭线程池
                pool.shutdown();
                // 获取所有并发任务的运行结果
                for (Future f : FutureList) {
                    Map<String,Object> map = new HashMap<String, Object>();
                    // 从Future对象上获取任务的返回值，并输出到控制台
                    map =  ( Map<String,Object>)f.get();
                    Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, Object> entry = it.next();
                        System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if("hanziList".equals(entry.getKey())){
                            list = (List<HanziIndex>)entry.getValue();
                        }
                    }
                }
            }


            try{

                //searchValue = tool.ios8859ByUtf8(searchValue);
                long searchEnd = System.currentTimeMillis();
                String text ="搜索索引  搜索关键字== --name=="+searchValue+"--ip=="+new Tool().getIP(request)+"-----花费时间:"+(searchEnd - searchStarts)+"毫秒";
                log.info("搜索索引  搜索关键字== --name=="+searchValue+"--ip=="+new Tool().getIP(request)+"-----花费时间:"+(searchEnd - searchStarts));
                //basicInfoService.saveObject(kw);
                
                /*PrintWriter out = response.getWriter();
                out.print(text);
                out.flush();
                out.close();*/
            }catch(Exception ex){
                log.info("保存出错");
                log.error(ex.getMessage());
                log.error(ex,ex);
            }
            json.setResult(list);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getStackTrace());
            json.setCode("0000");
        }finally {
            //list再排序
            /*Collections.sort(list, new Comparator<HanziIndex>(){

                
                 * int compare(Student o1, Student o2) 返回一个基本类型的整型，
                 * 返回负数表示：o1 小于o2，
                 * 返回0 表示：o1和o2相等，
                 * 返回正数表示：o1大于o2。
                 
                public int compare(HanziIndex o1, HanziIndex o2) {

                    //按照学生的年龄进行升序排列
                    if(o1.getId() < o2.getId()){
                        return 1;
                    }
                    if(o1.getId() == o2.getId()){
                        return 0;
                    }
                    return -1;
                }
            });*/
            //maps.put("searchValue", searchValue);
           
        }
        String strContent= JSON.toJSONString(list);
        maps.put("list", strContent);
        log.info("searchValue:"+searchValue+"\n"+"list:"+ JSON.toJSONString(list));
        return "luceneResult";
    }

}
