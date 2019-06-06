package com.sly.demo.thinkinjava.chapter10.innerclass;

interface A {

}

interface B {

}

class X implements A, B {

}

class Y implements A {
	B makeB() {
		return new B() {

		};
	}
}

/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public class MultiInterfaces {
	static void takesA(A a) {

	}

	static void takesB(B b) {

	}

	public static void main(String[] args) {
		X x = new X();
		Y y = new Y();
		takesA(x);
		takesA(y);
		takesB(x);
		takesB(y.makeB());
	}
}
