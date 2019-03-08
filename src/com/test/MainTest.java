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
		//a();
		int[] aa = {47,16,2,344,6000,95};
		//int[] aa = {4,1,2,3,16,51};
		b(aa);
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

	private static void b(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length-i-1; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}

			}

			System.out.print("第" + (i + 1) + "次外层循环");
			for (int q : a) {
				System.out.print(q + "\t");
			}
			System.out.println();
		}
		for (int w : a) {
			System.out.print(w + "\t");
		}
	}
}
