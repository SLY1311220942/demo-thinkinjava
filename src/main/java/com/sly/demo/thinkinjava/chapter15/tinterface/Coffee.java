package com.sly.demo.thinkinjava.chapter15.tinterface;

/**
 * 
 * @author sly
 * @time 2019年8月23日
 */
public class Coffee {
	private static long counter = 0;
	private final long id = counter++;

	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + id;
	}
}
