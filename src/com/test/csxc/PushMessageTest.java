package com.test.csxc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PushMessageTest {
	
	
	public static String diaoyong() {
		System.out.println(Thread.currentThread().getName() + "主线程运行 开始");

		PushMessage a = new PushMessage("001");
		PushMessage b = new PushMessage("002");
		PushMessage c = new PushMessage("003");
		PushMessage d = new PushMessage("004");
		
		//PushMessage pm = new PushMessage("001");
		 //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<String> result1 = new FutureTask<>(a);
        FutureTask<String> result2 = new FutureTask<>(b);
        FutureTask<String> result3 = new FutureTask<>(c);
        FutureTask<String> result4 = new FutureTask<>(d);
        
        new Thread(result1).start();
        new Thread(result2).start();
        new Thread(result3).start();
        new Thread(result4).start();
        
        try {
        	String sum1 = result1.get();
        	String sum2 = result2.get();
        	String sum3 = result3.get();
        	String sum4 = result4.get();
        	
			System.out.println("主线程运行 结束 sum1:"+sum1);
			System.out.println("主线程运行 结束 sum2:"+sum2);
			System.out.println("主线程运行 结束 sum3:"+sum3);
			System.out.println("主线程运行 结束 sum4:"+sum4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		System.out.println(Thread.currentThread().getName() + "主线程运行 结束");
		
		return "结束";
		

	}
	
	public static void main(String[] args) {
		String temp = diaoyong();
		System.out.println(temp);
	}

}
