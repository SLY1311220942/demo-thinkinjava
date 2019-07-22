package com.sly.demo.thinkinjava.chapter14.instclass;

class Base {
	
}

class Drived extends Base {
	
}

public class InstanceofClass {
	static void test(Object x) {
		System.out.println("Test x of type:" + x.getClass());
		System.out.println("x instanceof Base:" + (x instanceof Base));
		System.out.println("x instanceof Drived:" + (x instanceof Drived));
		System.out.println("Base.isInstance(x):" + Base.class.isInstance(x));
		System.out.println("Drived.isInstance(x):" + Drived.class.isInstance(x));
		System.out.println("x.getClass() == Base.class:" + (x.getClass() == Base.class));
		System.out.println("x.getClass() == Drived.class:" + (x.getClass() == Drived.class));
		System.out.println("x.getClass().equals(Base.class):" + (x.getClass().equals(Base.class)));
		System.out.println("x.getClass().equals(Drived.class):" + (x.getClass().equals(Drived.class)));
	}
	
	public static void main(String[] args) {
		test(new Base());
		test(new Drived());
	}
}

