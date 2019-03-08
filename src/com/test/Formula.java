package com.test;

/**
 * @author Hanley 2019年2月12日下午4:57:29
 *
 */
public interface Formula {

	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}
