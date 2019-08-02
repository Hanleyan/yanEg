package com.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
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
	
	@Test
	public void getRandomNumber1(){
		
		for (int i = 0; i < 100; i++) {
			//方法一： (数据类型)(最小值+Math.random()*(最大值-最小值+1))
			int a = (int)(0+Math.random()*(10-0+1));//[0,10]
			
			System.out.println(a);
		}
	}
	@Test
	public void getRandomNumber2(){
		
		/*for (int i = 0; i < 100; i++) {
			Random r = new Random();
			int a = r.nextInt(10)+1;//[0,10]
			
			System.out.println(a);
		}*/
		for (int i = 0; i < 100; i++) {
			Random r = new Random();
			int a = r.nextInt(2);//[0,2)
			
			System.out.println(a);
		}
	}
	
	@Test
	public void bigDecimalTest(){
		BigDecimal ordersPrice = new BigDecimal("0");
		
		BigDecimal b1 = new BigDecimal("3299.00");
		
		ordersPrice = ordersPrice.add(b1);
		System.out.println(ordersPrice);
		
	}
	
	@Test
	public void tTest(){
		
		System.out.println("你好  \n 你好");
		
	}
}
