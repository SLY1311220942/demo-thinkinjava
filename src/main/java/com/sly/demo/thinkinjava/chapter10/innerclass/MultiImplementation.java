package com.sly.demo.thinkinjava.chapter10.innerclass;

class D {

}

abstract class E {

}

class Z extends D {
	E makeE() {
		return new E() {

		};
	}
}

/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public class MultiImplementation {
	static void takeD(D d) {

	}

	static void takeE(E e) {

	}

	public static void main(String[] args) {
		Z z = new Z();
		takeD(z);
		takeE(z.makeE());
	}
}
