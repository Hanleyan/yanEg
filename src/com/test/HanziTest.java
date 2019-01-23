package com.test;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

public class HanziTest {
	
	/**
	 * 随机产生汉字  有繁体字
	 * @throws Exception
	 */
	@Test
	public void test(){
	
	String str="";
	
	for(int i=0;i<4;i++){
	
	  char c=(char)(0x4e00+(int) (Math.random()*(0x9fa5-0x4e00+1)));
	
		str+=c;
		}
	
		System.out.println(str);
	
	}
	
	/**
	 * 随机产生10个汉字
	 * @throws Exception
	 */
	@Test
	public  void test1() throws Exception { 
   
		String str = "";
		for(int i=0;i<10;i++){
			String stem = "";
	        int highPos, lowPos;
	        Random random = new Random();
	        highPos = (176 + Math.abs(random.nextInt(39)));
	        lowPos = 161 + Math.abs(random.nextInt(93));
	        byte[] b = new byte[2];
	        b[0] = (new Integer(highPos)).byteValue();
	        b[1] = (new Integer(lowPos)).byteValue();
	        stem = new String(b, "GB2312");
	        str += stem;
	        
		}

		System.out.println(str);
    }
	
	@Test
	public void test2() {

		try {
			int i = 100 / 0;
			System.out.print(i);
		} catch (Exception e) {
			System.out.print(1);
			throw new RuntimeException();
		} finally {
			System.out.print(2);
		}
		System.out.print(3);
	}
	

}
