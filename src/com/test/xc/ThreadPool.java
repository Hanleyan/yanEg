package com.test.xc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hanley 
 * 2018年11月21日上午11:10:50
 *
 */
public class ThreadPool {
	/*
	 * Java通过Executors提供四种线程池，分别为：
	 *	newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 *	newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	 *	newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	 *	newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	 * 
	 * */

	public static void createThreadPool(){
		
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
		    final int index = i;
		    try {
		        Thread.sleep(index * 100);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }

		    cachedThreadPool.execute(new Runnable() {

		        @Override
		        public void run() {
		            System.out.println(index);
		        }
		    });
		}
		System.out.println("ThreadPool.createThreadPool() over");
	}
	
	public static void main(String[] args) {
		createThreadPool();
	}
}
