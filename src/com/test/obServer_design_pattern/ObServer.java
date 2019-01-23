package com.test.obServer_design_pattern;
/**
*@作者：shaoli
*2018年9月29日下午11:18:24
* 读万卷书，行万里路
* 
* 设计模式之观察者模式
* 
*/

public abstract class ObServer {
	
	protected Subject subject;
	public abstract void update();

}
