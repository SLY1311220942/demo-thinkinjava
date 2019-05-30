package com.sly.demo.thinkinjava.chapter10.dotnew;

/**
 * .new
 * @author sly
 * @time 2019年5月28日
 */
public class DotNew {
	public class Inner {
		public Inner() {
			System.out.println("new inner");
		}
	}

	public static void main(String[] args) {
		DotNew dotNew = new DotNew();
		Inner inner = dotNew.new Inner();
	}
}
