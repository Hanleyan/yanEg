package com.test.demo1;
/**
 * @author Hanley 
 * 2019年7月29日上午11:48:34
 *
 *客户端主程序 
 */

public class MyClientSocket{
	public static void main(String[] args) {
		System.out.println("***我是客户端***");
		ClientThread cThread = new ClientThread("127.0.0.1",3333);
		cThread.start();
 
	}
}
