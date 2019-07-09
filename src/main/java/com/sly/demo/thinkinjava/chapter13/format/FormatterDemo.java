package com.sly.demo.thinkinjava.chapter13.format;

import java.util.Formatter;

/**
 * 
 * @author sly
 * @time 2019年7月9日
 */
public class FormatterDemo {
	public static void main(String[] args) {
		int x = 1;
		double y = 1.5;
		String str = "abcdegh";
		Formatter formatter = new Formatter(System.out);
		formatter.format("Row [%d %.1f]\n",  x, y);
		
		formatter.format("Row [%-10.20s]\n", str);
		formatter.format("Row [%10.20s]\n", str);
		formatter.close();
	}
}
