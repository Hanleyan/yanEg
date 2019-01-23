package com.test.obServer_design_pattern;
/**
*@作者：shaoli
*2018年9月29日下午11:48:44
* 读万卷书，行万里路
* 
* 实体观察者类三  OctalObserver
* 
*/

public class OctalObserver extends ObServer{

	public OctalObserver(Subject subject){
	      this.subject = subject;
	      this.subject.attach(this);
	  }
	
	@Override
	public void update() {
		/*System.out.println( "Octal String: " 
			     + Integer.toOctalString( subject.getState() ) ); */
		System.out.println( "我是Octal类 观察到Subject的state属性变化了，新state: "+subject.getState()); 
	}

}
