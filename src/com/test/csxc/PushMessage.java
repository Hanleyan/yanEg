package com.test.csxc;

import java.util.concurrent.Callable;

/*public class PushMessage extends Thread{ 
*/	
public class PushMessage implements Callable<String>{ //implements Callable<Boolean>
 
	private String name;
	private String methodcode;
	private int  a = 1;
	private int b = 2;
	private int c = 3;
	private String sum;

	public PushMessage(String name, String methodcode, int a, int b, int c) {
		super();
		this.name = name;
		this.methodcode = methodcode;
		this.a = a;
		this.b = b;
		this.c = c;
	}
	

	public PushMessage(String methodcode) {
		super();
		this.methodcode = methodcode;
	}

	public PushMessage() {
		super();
	}

	/*public void run() {
		if("001".equals(name) || "002".equals(name)){
			System.out.println("子线程"+name + "运行  ---- "+"发送Message给  "+name+" 已送到");
		}else if("003".equals(name) || "004".equals(name)){
			System.out.println("子线程"+name + "运行  ---- "+"发送Message给  "+name+" 未送到");
		}
		
		
		try {
			sleep((int)(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/

	@Override
	public String call() throws Exception {
		if("001".equals(methodcode)){
			sum=a+b+c+methodcode;
			System.out.println("子线程"+methodcode + "运行  ---- "+"发送Message给  "+methodcode+" 已送到-------结果：sum:"+sum);
		}else if("002".equals(methodcode)){
			sum=a+b+c+methodcode;
			System.out.println("子线程"+methodcode + "运行  ---- "+"发送Message给  "+methodcode+" 未送到-------结果：sum:"+sum);
		}else if("003".equals(methodcode)){
			sum=a+b+c+methodcode;
			System.out.println("子线程"+methodcode + "运行  ---- "+"发送Message给  "+methodcode+" 未送到-------结果：sum:"+sum);
		}else if("004".equals(methodcode)){
			sum=a+b+c+methodcode;
			System.out.println("子线程"+methodcode + "运行  ---- "+"发送Message给  "+methodcode+" 未送到-------结果：sum:"+sum);
		}
		
	
		return sum;
	}
	
	public int getA() {
		return a;
	}

	public String getMethodcode() {
		return methodcode;
	}

	public void setMethodcode(String methodcode) {
		this.methodcode = methodcode;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSum() {
		return sum;
	}


	public void setSum(String sum) {
		this.sum = sum;
	}

}
