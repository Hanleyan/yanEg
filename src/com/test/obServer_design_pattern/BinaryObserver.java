package com.test.obServer_design_pattern;

/**
*@作者：shaoli
*2018年9月29日下午11:43:51
* 读万卷书，行万里路
* 
* 实体观察者类一 BinaryObserver
* 
*/

public class BinaryObserver extends ObServer{

	public BinaryObserver (Subject subject){
		this.subject=subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		/*System.out.println( "Binary String: " 
			      + Integer.toBinaryString( subject.getState() ) );*/ 
		System.out.println( "我是Binary类 观察到Subject的state属性变化了，新state: "+subject.getState()); 
	}

}
