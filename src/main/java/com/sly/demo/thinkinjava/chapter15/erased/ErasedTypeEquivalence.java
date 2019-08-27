package com.sly.demo.thinkinjava.chapter15.erased;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author sly
 * @time 2019年8月27日
 */
public class ErasedTypeEquivalence {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Class c1 = new ArrayList<Integer>().getClass();
		Class c2 = new ArrayList<String>().getClass();
		System.out.println(c1 == c2);
		
		System.out.println(Arrays.toString(c1.getTypeParameters()));
		System.out.println(Arrays.toString(c2.getTypeParameters()));
	}
}

