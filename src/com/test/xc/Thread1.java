package com.test.xc;

public class Thread1 extends Thread{

	private String name;

	public Thread1(String name) {
		super();
		this.name = name;
	}
	
	public void run(){
		System.out.println(name+ " 线程运行开始");
		for (int i = 0; i < 5; i++) {
			
			System.out.println("Thread1 的子线程"+name + "运行 : " + i);
			
			try {
				sleep((int)(Math.random()*10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println( name+ " 线程运行结束");
	}
}
