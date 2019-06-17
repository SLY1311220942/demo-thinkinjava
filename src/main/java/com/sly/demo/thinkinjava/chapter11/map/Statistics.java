package com.sly.demo.thinkinjava.chapter11.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author sly
 * @time 2019年6月17日
 */
public class Statistics {
	public static void main(String[] args) {
		Random random = new Random(47);
		Map<Integer, Integer> result = new HashMap<>();
		for (int i = 0; i < 10000; i++) {
			int nextInt = random.nextInt(20);
			Integer integer = result.get(nextInt);
			result.put(nextInt, integer == null ? 1 : integer + 1);
		}
		System.out.println(result);
	}
}

