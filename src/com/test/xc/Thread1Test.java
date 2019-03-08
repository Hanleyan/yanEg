package com.test.xc;

public class Thread1Test {
	
	public static void main(String[] args) {
		
		/**
		 * 注意：start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。
		 * 从程序运行的结果可以发现，多线程程序是乱序执行。因此，只有乱序执行的代码才有必要设计为多线程。
		 * Thread.sleep()方法调用目的是不让当前线程独自霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
		 * 实际上所有的多线程代码执行顺序都是不确定的，每次执行的结果都是随机的。
		 */
		System.out.println(Thread.currentThread().getName()+ "主线程运行 开始");
		//Thread1 t1 = new Thread1("A");
		new Thread1("A").start();
		Thread1 t2 = new Thread1("B");
		Thread1 t3 = new Thread1("C");
		
		//t1.start();
		t2.start();
		t3.start();
		System.out.println(Thread.currentThread().getName()+ "主线程运行 结束");
	}

}
