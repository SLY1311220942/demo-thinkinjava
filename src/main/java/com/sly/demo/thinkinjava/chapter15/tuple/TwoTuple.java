package com.sly.demo.thinkinjava.chapter15.tuple;

/**
 * 
 * @author sly
 * @time 2019年8月21日
 */
public class TwoTuple<A, B> {
	public final A first;
	public final B second;

	public TwoTuple(A a, B b) {
		first = a;
		second = b;
	}

	@Override
	public String toString() {
		return "first:" + first + " second:" + second;
	}
}
