package com.sly.demo.thinkinjava.chapter11.print;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 
 * @author sly
 * @time 2019年6月14日
 */
public class PrintContainer {
	
	public static Collection<String> fill(Collection<String> collection) {
		collection.add("cat");
		collection.add("dog");
		collection.add("bird");
		collection.add("dog");
		return collection;
	}
	
	public static Map<String,String> fill(Map<String,String> map){
		map.put("cat", "猫");
		map.put("dog", "狗");
		map.put("bird", "鸟");
		map.put("dog", "狗");
		return map;
	}
	
	public static void main(String[] args) {	
		System.out.println(fill(new ArrayList<>()));
		System.out.println(fill(new LinkedList<>()));
		System.out.println(fill(new HashSet<>()));
		System.out.println(fill(new TreeSet<>()));
		System.out.println(fill(new LinkedHashSet<>()));
		
		System.out.println(fill(new HashMap<>()));
		System.out.println(fill(new TreeMap<>()));
		System.out.println(fill(new LinkedHashMap<>()));
	}
}

