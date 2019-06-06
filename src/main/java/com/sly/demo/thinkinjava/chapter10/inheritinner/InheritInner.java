package com.sly.demo.thinkinjava.chapter10.inheritinner;

class WithInner {
	class Inner {

	}
}

public class InheritInner extends WithInner.Inner {
	InheritInner(WithInner wi) {
		wi.super();
	}
	
	public static void main(String[] args) {
		WithInner inner = new WithInner();
		InheritInner inheritInner = new InheritInner(inner);
	}
}
