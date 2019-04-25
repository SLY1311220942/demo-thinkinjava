package com.sly.demo.thinkinjava.chapter05;

/**
 * 
 * @author sly
 * @time 2019年4月25日
 */
public class Father {

	static {
		System.out.println("父类静态代码块执行!");
	}
	
	{
		System.out.println("父类代码块执行!");
	}
	
	public Father() {
		System.out.println("父类构造方法执行!");
	}
	
	
}

