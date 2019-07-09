package com.sly.demo.thinkinjava.chapter13.regex;

import org.junit.Test;

/**
 * 
 * @author sly
 * @time 2019年7月9日
 */
public class RegexDemo {
	@Test
	public void test01() {
		String regex = "-?\\d+";
		String str = "-158";
		System.out.println(str.matches(regex));
	}
}

