package com.sly.demo.thinkinjava.chapter13.format;

/**
 * 
 * @author sly
 * @time 2019年7月9日
 */
public class FormatDemo {
	public static void main(String[] args) {
		int x = 1;
		double y = 1.5;
		System.out.println("Row [" + x + " " + y + "]");
		System.out.format("Row [%d %.1f]\n", x, y);
		System.out.printf("Row [%d %.1f]\n", x, y);
	}
}
