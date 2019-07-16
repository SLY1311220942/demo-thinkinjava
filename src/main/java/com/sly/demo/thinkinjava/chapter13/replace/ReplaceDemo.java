package com.sly.demo.thinkinjava.chapter13.replace;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
	
	@Test
	public void test05() {
		float a = 1.0f - 0.9f;	
        float b = 0.9f - 0.8f;	
        if (a == b) {	
            System.out.println("true");	
        } else {	
            System.out.println("false");	
        }
	}
	
	@Test
	public void test06() {
		Float a = Float.valueOf(1.0f - 0.9f);	
        Float b = Float.valueOf(0.9f - 0.8f);	
        if (a.equals(b)) {	
            System.out.println("true");	
        } else {	
            System.out.println("false");	
        }
	}
	
	@Test
	public void test07() {
		String param = null;	
        switch (param) {	
            case "null":	
                System.out.println("null");	
                break;	
            default:	
                System.out.println("default");	
        }
	}
	
	@Test
	public void test08() {
		BigDecimal a = new BigDecimal(0.1);
		System.out.println(a);
		BigDecimal b = new BigDecimal("0.1");
		System.out.println(b);
	}
	
	private final static Lock lock = new ReentrantLock();
	private boolean tryLock;
	
	@Test
	public void test09() {
		try {	
            tryLock = lock.tryLock();
            System.out.println(tryLock);
        } catch (Exception e) {	
            e.printStackTrace();	
        } finally {	
            lock.unlock();	
        }	
	}
}

