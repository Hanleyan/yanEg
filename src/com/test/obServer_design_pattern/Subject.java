package com.test.obServer_design_pattern;

import java.util.ArrayList;
import java.util.List;

/**
*@作者：shaoli
*2018年9月29日下午11:34:57
* 读万卷书，行万里路
*/

public class Subject {
	
	private List<ObServer> Observers = new ArrayList<ObServer>();
	private int state;
	
	public List<ObServer> getObservers() {
		return Observers;
	}
	public void setObservers(List<ObServer> observers) {
		Observers = observers;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
		notifyAllObservers();
	}
	public void attach(ObServer observer){
		Observers.add(observer);      
	   }
	public void notifyAllObservers(){
		for (ObServer observer : Observers) {
			observer.update();
		}
	}
	
	

}
