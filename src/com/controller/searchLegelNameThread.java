package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSON;
import com.entity.Customer;
import com.entity.HanziIndex;
import com.util.luceneUtil.SearchLuceneUtil;

public class searchLegelNameThread implements Callable<Object>  {

    private String searchName;
    private String legelIndexUrl;
    private Boolean legelBool = false;
    private Boolean codeBool = false;
    private int pageSize = 15;
    private int pageNo = 1;
    private Integer legelCount = 0;

    public searchLegelNameThread(String searchName, String legelIndexUrl, Boolean legelBool, Boolean codeBool, int pageSize, int pageNo, Integer legelCount) {
        this.searchName = searchName;
        this.legelIndexUrl = legelIndexUrl;
        this.legelBool = legelBool;
        this.codeBool = codeBool;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.legelCount = legelCount;
    }

    public Object call() throws Exception {
        System.out.println("legelCount=="+legelCount+"--legelBool=="+legelBool+"--searchName=="+searchName);
        List<HanziIndex> list = new ArrayList<HanziIndex>();
        Map<String,Object> maps = new HashMap<String, Object>();
        
        list =  SearchLuceneUtil.getIndexListByCodeList(searchName,pageNo,pageSize);
        /*if(legelBool && legelCount==0){
            list =  SearchLuceneUtil.getIndexListByLegelName(searchName,pageNo,pageSize);
            System.out.println("深圳市");
            maps.put("legelList",list);
            return maps;
        }else{
            if(codeBool){
                //搜索3ACN 统一征信代码 组织结构代码 注册号
                list =  SearchLuceneUtil.getIndexListByCodeList(searchName,pageNo,pageSize);
                maps.put("nameList",list);
                return maps;
            }else{
                if(legelBool){
                    if(legelCount>1){
                        list = SearchLuceneUtil.getIndexListByCodeNameCNAndEN(searchName, pageNo, pageSize);//搜索中英文公司名称
                        maps.put("addressList",list);
                        System.out.println("搜索中英文公司名称");
                        return maps;
                    }else{
                        list = SearchLuceneUtil.getIndexListByCodeAddressCN(searchName, pageNo, pageSize);//搜索中文地址
                        maps.put("nameList",list);
                        System.out.println("搜索中文地址");
                        return maps;
                    }
                }else{
                    if(legelCount>0){
                        list = SearchLuceneUtil.getIndexListByCodeNameCNAndEN(searchName, pageNo, pageSize);//搜索中英文公司名称
                        maps.put("addressList",list);
                        System.out.println("搜索中英文公司名称");
                        return maps;
                    }else{
                        list = SearchLuceneUtil.getIndexListByCodeAddressCN(searchName, pageNo, pageSize);//搜索中文地址
                        maps.put("nameList",list);
                        System.out.println("搜索中文地址");
                        return maps;
                    }
                }
            }
        }*/

        System.out.println("搜索中文" + "结果 list："+ JSON.toJSONString(list));
        maps.put("hanziList",list);
        return maps;


    }



}
