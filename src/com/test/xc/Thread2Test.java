package com.test.xc;

public class Thread2Test {
	public static void main(String[] args) {
		
		/**
		 * Thread2类通过实现Runnable接口，使得该类有了多线程类的特征。run（）方法是多线程程序的一个约定。所有的多线程代码都在run方法里面。Thread类实际上也是实现了Runnable接口的类。
	 	 * 在启动的多线程的时候，需要先通过Thread类的构造方法Thread(Runnable target) 构造出对象，然后调用Thread对象的start()方法来运行多线程代码。
	   	 * 实际上所有的多线程代码都是通过运行Thread的start()方法来运行的。因此，不管是扩展Thread类还是实现Runnable接口来实现多线程，
	   	 * 最终还是通过Thread的对象的API来控制线程的，熟悉Thread类的API是进行多线程编程的基础。
		 */
		System.out.println(Thread.currentThread().getName()+ "主线程运行 开始");
		new Thread(new Thread2("D")).start();
		new Thread(new Thread2("E")).start();
		new Thread(new Thread2("F")).start();
		
		
		//Thread1中线程也一起跑
		Thread1 t1 = new Thread1("A");
		Thread1 t2 = new Thread1("B");
		Thread1 t3 = new Thread1("C");
		
		t1.start();
		t2.start();
		t3.start();
		
		/**
		 * Thread和Runnable的区别
		 * 如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。
	   	 * 总结：
		 * 实现Runnable接口比继承Thread类所具有的优势：
		 * 1）：适合多个相同的程序代码的线程去处理同一个资源
		 * 2）：可以避免java中的单继承的限制
		 * 3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
		 * 4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类
		 * 提醒一下大家：main方法其实也是一个线程。在java中所以的线程都是同时启动的，至于什么时候，哪个先执行，完全看谁先得到CPU的资源。
		 * 在java中，每次程序运行至少启动2个线程。一个是main线程，一个是垃圾收集线程。因为每当使用java命令执行一个类的时候，实际上都会启动一个ＪＶＭ，每一个ｊＶＭ实习在就是在操作系统中启动了一个进程。
		 */
		System.out.println(Thread.currentThread().getName()+ "主线程运行 结束");
	}

}
