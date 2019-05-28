package com.sly.demo.thinkinjava.chapter10.sequence;

/**
 * 内部类可以访问外围类的方法和字段
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Sequence {
	private Object[] items;
	private int next = 0;

	public Sequence(int size) {
		items = new Object[size];
	}

	public void add(Object object) {
		if (next < items.length) {
			items[next++] = object;
		}
	}

	private class SequenceSelector implements Selector {
		private int i = 0;

		@Override
		public boolean end() {
			return i == items.length;
		}

		@Override
		public Object current() {
			return items[i];
		}

		@Override
		public void next() {
			if (i <= items.length) {
				i++;
			}
		}

	}

	public Selector selector() {
		return new SequenceSelector();
	}

	public static void main(String[] args) {
		Sequence sequence = new Sequence(10);
		for (int i = 0; i < 10; i++) {
			sequence.add(Integer.toString(i));
		}
		Selector selector = sequence.selector();
		while (!selector.end()) {
			System.out.println(selector.current());
			selector.next();
		}
	}

}
