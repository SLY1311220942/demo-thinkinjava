package com.sly.demo.thinkinjava.chapter10.parcel10;

/**
 * 
 * @author sly
 * @time 2019年5月30日
 */
public abstract class Base {
	public Base(int i) {
		System.out.println("Base Constructor i = " + i);
	}

	abstract void f();
}
