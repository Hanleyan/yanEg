package com.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

/**
 * @author Hanley 
 * 2019年7月29日下午2:26:02
 *
 */
public class JunitTest {

	@Test
	public void test() {
		int a = 3;
		int b = 5 / 4;
		System.out.println(b);
	}
	
	@Test
	public void get(){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderNum = "";
		
		
		
		//orderNum = System.currentTimeMillis()+"";
		orderNum = format.format(new Date());
		System.out.println(orderNum);
	}
}
