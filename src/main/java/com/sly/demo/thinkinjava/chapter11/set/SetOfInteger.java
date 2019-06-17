package com.sly.demo.thinkinjava.chapter11.set;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author sly
 * @time 2019年6月17日
 */
public class SetOfInteger {
	
	public static void main(String[] args) {
		Random random = new Random(47);
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < 10000; i++) {
			set.add(random.nextInt(30));
		}
		System.out.println(set);
	}
}

