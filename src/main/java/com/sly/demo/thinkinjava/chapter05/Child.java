package com.sly.demo.thinkinjava.chapter05;

/**
 * 
 * @author sly
 * @time 2019年4月25日
 */
public class Child extends Father {
	static {
		System.out.println("子类静态代码块执行!");
	}
	
	{
		System.out.println("子类代码块执行!");
	}

	public Child() {
		System.out.println("子类构造方法执行");
	}
	
	
}

