package com.sly.demo.thinkinjava.chapter13.patternandmatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * @author sly
 * @time 2019年7月9日
 */
public class PatternAndMatcherDemo {
	
	@Test
	public void test01() {
		Pattern pattern = Pattern.compile("[\\d]");
		Matcher matcher = pattern.matcher("123456");
		while(matcher.find()) {
			System.out.println("match:" + matcher.group() + " at position " + matcher.start() + "-" + (matcher.end()-1));
		}
	}
	
	@Test
	public void test02() {
		Pattern pattern = Pattern.compile("[\\d]");
		String[] split = pattern.split("a1cd5xd");
		for (String str : split) {
			System.out.println(str);
		}
	}
	
	@Test
	public void test03() {
		boolean matches = Pattern.matches("[\\d]", "1");
		System.out.println(matches);
	}
	
	@Test
	public void test04() {
		String str = "Twas brilling, and the slithy toves\n" +
				"Did gyre and gimble in the wabe.\n" +
				"All mimsy were the borogoves,\n" +
				"And the mome raths outgrabe.\n\n" +
				"Beware the Jabberwock, my son,\n" +
				"The jaws that bite, and shun\n" +
				"The frumious Bandersnatch.";
		
		Matcher matcher = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(str);
		while(matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.print("[" + matcher.group(i) + "]");
			}
			System.out.println();
		}
	}
	
	@Test
	public void test05() {
		Matcher matcher = Pattern.compile("[\\d]").matcher("123456");
		System.out.println(matcher.start());
	}
}

