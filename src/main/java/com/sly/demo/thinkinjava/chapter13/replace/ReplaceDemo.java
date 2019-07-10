package com.sly.demo.thinkinjava.chapter13.replace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * @author sly
 * @time 2019年7月10日
 */
public class ReplaceDemo {
	
	@Test
	public void test01() {
		Matcher matcher = Pattern.compile("[\\d]").matcher("asd2sd3f4s5");
		String replaceFirst = matcher.replaceFirst("数");
		System.out.println(replaceFirst);
	}
	
	@Test
	public void test02() {
		Matcher matcher = Pattern.compile("[\\d]").matcher("asd2sd3f4s5");
		String replaceAll = matcher.replaceAll("数");
		System.out.println(replaceAll);
	}
	
	@Test
	public void test03() {
		Matcher matcher = Pattern.compile("[\\d]").matcher("asd2sd3f4s5");
		StringBuffer sb = new StringBuffer();
		int count = 0;
		int total = 3;
		while (matcher.find() && count < total) {
			matcher.appendReplacement(sb, "数");
			count ++;
		}
		matcher.appendTail(sb);
		System.out.println(sb);
	}
	
	@Test
	public void test04() {
		Matcher matcher = Pattern.compile("[\\d]").matcher("asd2sd3f4s5");
		while (matcher.find()) {
			System.out.print(matcher.group() + " ");
		}
		System.out.println();
		matcher.reset("asd6sd7f8s9");
		while (matcher.find()) {
			System.out.print(matcher.group() + " ");
		}
	}
}

