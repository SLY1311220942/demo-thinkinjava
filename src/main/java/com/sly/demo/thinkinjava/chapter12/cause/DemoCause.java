package com.sly.demo.thinkinjava.chapter12.cause;

/**
 * 
 * @author sly
 * @time 2019年7月2日
 */
public class DemoCause {
	static void f() {
		throw new RuntimeException();
	}

	static void g() {
		try {
			f();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	static void h() {
		try {
			g();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public static void main(String[] args) {
		h();
	}
}
