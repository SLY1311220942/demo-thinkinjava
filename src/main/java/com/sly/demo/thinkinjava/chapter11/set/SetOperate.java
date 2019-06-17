package com.sly.demo.thinkinjava.chapter11.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author sly
 * @time 2019年6月17日
 */
public class SetOperate {
	public static void main(String[] args) {
		String str1 = "A B C D E F G H I J K L";
		String str2 = "H I J K L";
		String str3 = "X Y Z";
		
		Set<String> set1 = new HashSet<>(Arrays.asList(str1.split(" ")));
		set1.add("M");
		System.out.println("H:" + set1.contains("H"));
		System.out.println("N:" + set1.contains("N"));
		
		Set<String> set2 = new HashSet<>(Arrays.asList(str2.split(" ")));
		System.out.println("set2 in set1:" + set1.containsAll(set2));
		set1.remove("H");
		System.out.println("remove H set1:" + set1);
		System.out.println("set2 in set1:" + set1.containsAll(set2));
		set1.removeAll(set2);
		System.out.println("set2 removed from set1:" + set1);
		set1.addAll(Arrays.asList(str3.split(" ")));
		System.out.println("X Y Z added to set1:" + set1);
	}
}

