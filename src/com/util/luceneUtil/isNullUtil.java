package com.util.luceneUtil;

public class isNullUtil {

    public static String isStrNull(String str){
        if(null!=str && !"".equals(str) && !"null".equals(str)){
            return str;
        }else{
            return "";
        }
    }
}
