package com.test;
/**
 * @author Hanley 
 * 2019年3月8日下午2:19:41
 *
 */
public class Singleton {
	
    private static Singleton INSTANCE = new Singleton();

    private Singleton(){}

    public  static Singleton getInstance(){
    	if(INSTANCE==null){
    		synchronized(Singleton.class){
        		INSTANCE = new Singleton();
        	}
    	}
        return INSTANCE;
    }
    
    
    public static void main(String[] args) {
    	Singleton s1 = new Singleton();
    	Singleton s2 = new Singleton();
    	Singleton s3 = new Singleton();
    	System.out.println(s1);
    	System.out.println(s2);
    	System.out.println(s3);
    }
}
