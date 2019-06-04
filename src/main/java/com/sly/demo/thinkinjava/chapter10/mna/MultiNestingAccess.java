package com.sly.demo.thinkinjava.chapter10.mna;

import com.sly.demo.thinkinjava.chapter10.mna.MNA.A;
import com.sly.demo.thinkinjava.chapter10.mna.MNA.A.B;

/**
 * 
 * @author sly
 * @time 2019年6月4日
 */
class MNA {
	private void f() {
		System.out.println("fff");
	}

	class A {
		private void g() {
			System.out.println("ggggg");
		}

		public class B {
			void h() {
				f();
				g();
			}
		}
	}
}

public class MultiNestingAccess {
	public static void main(String[] args) {
		MNA mna = new MNA();
		A mnaa = mna.new A();
		B mnaab = mnaa.new B();

		mnaab.h();
	}
}
