package com.test.obServer_design_pattern;
/**
*@作者：shaoli
*2018年9月29日下午11:50:39
* 读万卷书，行万里路
* 
* 观察者模式demo
* 
* 使用 Subject 和实体观察者对象。
* 
*/

public class ObserverPatternDemo {

	public static void main(String[] args) {
		Subject subject = new Subject();
		 
	      new HexaObserver(subject);
	      new OctalObserver(subject);
	      new BinaryObserver(subject);
	      
	      System.out.println("First state change: 15");   
	      subject.setState(15);
	      System.out.println("Second state change: 10");  
	      subject.setState(10);
	}

}
