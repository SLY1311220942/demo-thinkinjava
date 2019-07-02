package com.sly.demo.thinkinjava.chapter12.stacktrace;

/**
 * 
 * @author sly
 * @time 2019年7月2日
 */
public class WhoCalled {
	static void f() {
		try {
			throw new RuntimeException();
		} catch (Exception e) {
			for (StackTraceElement stackTraceElement : e.getStackTrace()) {
				System.out.println(stackTraceElement.getMethodName());
			}
		}
	}
	
	static void g() {
		f();
	}
	
	static void h() {
		g();
	}
	
	public static void main(String[] args) {
		f();
		System.out.println("-----------------------");
		g();
		System.out.println("-----------------------");
		h();
	}
}
