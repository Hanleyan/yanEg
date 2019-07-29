package com.test.demo1;
/**
 * @author Hanley 
 * 2019年7月29日上午11:47:25
 *
 */

/**
 * 基于TCP的Socket的编程    服务端
 * @author NullChen
 *
 */
public class MyServerSocket{
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("***我是服务器***");
		ServerThread sThread = new ServerThread(3333);
		sThread.start();
	}
}
