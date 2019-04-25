package com.sly.demo.thinkinjava.chapter05;

import org.junit.Test;

/**
 * 
 * @author sly
 * @time 2019年4月25日
 */
public class Demo {
	
	public static void func_01(int a) {
		System.out.println("int:" + a);
	}
	
	public static void func_01(short a) {
		System.out.println("short:" + a);
	}
	
	public static void func_01(long a) {
		System.out.println("long:" + a);
	}
	
	@Test
	public void test_01() {
		short a = 1;
		char b = 'a';
		byte c = 1;
		//找到了就是这个类型
		func_01(a);
		//会被类型提升当做int处理
		func_01(b);
		//会向上提升为short
		func_01(c);
	}
	
	@Test
	public void test_02() {
		//System.out.println("========father========");
		//new Father();
		System.out.println("========child========");
		new Child();
		System.out.println("========father========");
		new Father();
	}
}

