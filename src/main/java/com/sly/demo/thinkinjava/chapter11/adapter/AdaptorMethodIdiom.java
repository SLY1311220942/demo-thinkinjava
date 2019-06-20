package com.sly.demo.thinkinjava.chapter11.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author sly
 * @time 2019年6月20日
 */
class ReversibleArrayList<T> extends ArrayList<T> {
	private static final long serialVersionUID = -4515694757645112979L;

	public ReversibleArrayList(Collection<T> c) {
		super(c);
	}

	public Iterable<T> reserved() {
		return new Iterable<T>() {

			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					int current = size() - 1;

					@Override
					public boolean hasNext() {
						return current > -1;
					}

					@Override
					public T next() {
						return get(current--);
					}

				};
			}

		};
	}
}

/**
 * 
 * @author sly
 * @time 2019年6月20日
 */
public class AdaptorMethodIdiom {

	public static void main(String[] args) {
		ReversibleArrayList<String> reversibleArrayList = new ReversibleArrayList<>(
				Arrays.asList("To be or not to be".split(" ")));

		for (String str : reversibleArrayList) {
			System.out.print(str + " ");
		}
		System.out.println();
		for (String str : reversibleArrayList.reserved()) {
			System.out.print(str + " ");
		}
		System.out.println();
	}
}
