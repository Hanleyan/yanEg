package com.test.xc;
/**
 * @author Hanley 
 * 2019年3月8日上午11:49:31
 *
 */
public class Ticket implements Runnable {
	private int num;// 票数量
	private boolean flag = true;// 若为false则售票停止
	private int count=1;// 第几次卖票

	public Ticket(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		while (flag) {
			ticket(count);
			count+=1;
		}
	}

	/**
	 * 出票比预计的多了而且会出现多个人争抢做同一个座位的风险。如果是单个售票窗口是不会出现这种问题，
	 * 多窗口同时售票就会出现争抢共享资源因此紊乱的现象，解决该现象也很简单，
	 * 就是在ticket()方法前面加上synchronized关键字或者将ticket()方法的方法体完全用synchronized块包括起来。
	 * @param count
	 */
	private synchronized void ticket(int count) {
		//synchronized(this){
			if (num <= 0) {
				flag = false;
				return;
			}
			try {
				Thread.sleep(50);// 模拟延时操作
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 输出当前窗口号以及出票序列号
			System.out.println(Thread.currentThread().getName() + "售出票序列号：" + num-- +"  第"+ count++ +"次卖票" );
		}
	//}
	
	/*private void ticket(int count) {
		synchronized(this){
			if (num <= 0) {
				flag = false;
				return;
			}
			try {
				Thread.sleep(50);// 模拟延时操作
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 输出当前窗口号以及出票序列号
			System.out.println(Thread.currentThread().getName() + "售出票序列号：" + num-- +"  第"+ count++ +"次卖票" );
		}
	}*/
}
    
