package com.sly.demo.thinkinjava.chapter14.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;

	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		return null;
	}

}

public class SimpleDynamicProxyDemo {
	public static void customer(Interface iface) {
		iface.doSomthing();
		iface.somthingElse("bonobo");
	}
}
