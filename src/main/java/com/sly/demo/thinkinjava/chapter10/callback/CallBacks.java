package com.sly.demo.thinkinjava.chapter10.callback;

interface Incrementable {
	void increment();
}

class Callee1 implements Incrementable {
	private int i = 0;
	@Override
	public void increment() {
		i ++;
		System.out.println(i);
	}

}

class MyIncrement {
	public void increment() {
		System.out.println("other operation");
	}

	static void f(MyIncrement myIncrement) {
		myIncrement.increment();
	}
}

class Callee2 extends MyIncrement {
	private int i = 0;

	public void increment() {
		super.increment();
		i++;
		System.out.println(i);
	}

	private class Closure implements Incrementable {
		@Override
		public void increment() {
			Callee2.this.increment();
		}
	}

	Incrementable getCallBackReference() {
		return new Closure();
	}
}

class Caller {
	private Incrementable callbackReference;

	Caller(Incrementable incrementable) {
		callbackReference = incrementable;
	}

	void go() {
		callbackReference.increment();
	}
}

public class CallBacks {
	public static void main(String[] args) {
		Callee1 callee1 = new Callee1();
		Callee2 callee2 = new Callee2();
		
		MyIncrement.f(callee2);
		
		Caller caller1 = new Caller(callee1);
		Caller caller2 = new Caller(callee2.getCallBackReference());
		
		System.out.println("===========");
		
		caller1.go();
		caller1.go();
		System.out.println("===========");
		caller2.go();
		caller2.go();
	}
}
