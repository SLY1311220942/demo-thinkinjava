package com.sly.demo.thinkinjava.chapter10.dotthis;

/**
 * .this
 * @author sly
 * @time 2019年5月28日
 */
public class DotThis {
	public void f() {
		System.out.println("DotThis.this.f()");
	}
	
	public class Inner {
		public void f() {
			System.out.println("Inner.f()");
		}
		
		public DotThis outer() {
			return DotThis.this;
		} 
	}
	
	public Inner inner() {
		return new Inner();
	}
	
	public static void main(String[] args) {
		DotThis dotThis = new DotThis();
		DotThis.Inner inner = dotThis.inner();
		inner.outer().f();
		inner.f();
	}
}

