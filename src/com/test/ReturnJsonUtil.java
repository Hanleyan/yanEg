package com.test;

import java.util.HashMap;

/**
 * @author Hanley 
 * 2018年12月4日下午5:22:37
 *
 */
public class ReturnJsonUtil extends HashMap<String, Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReturnJsonUtil() {
    }

    public static ReturnJsonUtil returnSuccess() {
    	ReturnJsonUtil returnMap = new ReturnJsonUtil();
        returnMap.put("success", true);
        return returnMap;
    }
    
    public static ReturnJsonUtil returnSuccessWithMsg(String msg) {
    	ReturnJsonUtil returnMap = new ReturnJsonUtil();
        returnMap.put("msg", msg);
        returnMap.put("success", true);
        return returnMap;
    }
}
