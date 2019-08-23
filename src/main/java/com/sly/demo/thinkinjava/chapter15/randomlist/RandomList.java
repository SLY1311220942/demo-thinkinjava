package com.sly.demo.thinkinjava.chapter15.randomlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author sly
 * @time 2019年8月23日
 */
public class RandomList<T> {
	private List<T> storage = new ArrayList<T>();
	private Random random = new Random(47);
	
	public void add(T item) {
		storage.add(item);
	}
	
	public T select() {
		return storage.get(random.nextInt(storage.size()));
	}
	
	public static void main(String[] args) {
		RandomList<String> list = new RandomList<>();
		for (String str : "you are right".split(" ")) {
			list.add(str);
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println(list.select());
		}
	}
}
