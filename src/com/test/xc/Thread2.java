package com.test.xc;


public class Thread2 implements Runnable{

	private String name;

	public Thread2(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name+ " 线程运行开始");
		for (int i = 0; i < 5; i++) {
			System.out.println(name+"-"+i);
			
			try {
				Thread.sleep((int)(Math.random()*10));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(name+ " 线程运行结束");
		
	}
	
	
}
