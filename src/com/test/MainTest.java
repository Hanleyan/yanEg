package com.test;

import org.apache.log4j.Logger;

import com.service.APPServer;

/**
 * @author Hanley 
 * 2018年12月11日下午2:20:25
 *
 */
public class MainTest {
	private static final Logger log = Logger.getLogger(MainTest.class);
	
	public static void main(String[] args) {
		a();
	}
	
	private static void a(){
		try {
			int i = 100 / 0;
			System.out.println(i);
			System.out.println(0);
		} catch (Exception e) {
			System.out.println(1);
			//throw new RuntimeException();	
			e.printStackTrace();
			log.error(e,e);
		} finally {
			System.out.println(2);
		}
		System.out.println(3);
	}
}
