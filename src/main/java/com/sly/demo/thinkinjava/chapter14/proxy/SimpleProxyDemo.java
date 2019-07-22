package com.sly.demo.thinkinjava.chapter14.proxy;

interface Interface {
	void doSomthing();
	void somthingElse(String arg);
}

class RealObject implements Interface {

	@Override
	public void doSomthing() {
		System.out.println("doSomthing");
	}

	@Override
	public void somthingElse(String arg) {
		System.out.println("somthingElse " + arg);
	}

}

class SimpleProxy implements Interface{
	private Interface proxied;
	
	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}
	
	@Override
	public void doSomthing() {
		System.out.println("SimpleProxy doSomthing");
		proxied.doSomthing();
	}

	@Override
	public void somthingElse(String arg) {
		System.out.println("SimpleProxy somthingElse " + arg);
		proxied.somthingElse(arg);
	}
	
}

public class SimpleProxyDemo {
	public static void customer(Interface iface) {
		iface.doSomthing();
		iface.somthingElse("bonobo");
	}
	
	public static void main(String[] args) {
		customer(new RealObject());
		System.out.println("================");
		customer(new SimpleProxy(new RealObject()));
	}
}
