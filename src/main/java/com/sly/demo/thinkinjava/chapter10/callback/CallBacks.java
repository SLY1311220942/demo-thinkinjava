package com.sly.demo.thinkinjava.chapter10.callback;

interface Incrementable {
	void increment();
}

class Callee1 implements Incrementable {
	@Override
	public void increment() {

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
		
	}
}
