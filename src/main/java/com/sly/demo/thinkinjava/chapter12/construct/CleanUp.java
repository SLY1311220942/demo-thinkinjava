package com.sly.demo.thinkinjava.chapter12.construct;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 
 * @author sly
 * @time 2019年7月3日
 */
public class CleanUp {
	public static void main(String[] args) {
		try {
			InputStream in = new FileInputStream("asdasda");
			try {
				System.out.println("operate");
			} finally {
				System.out.println("finally run");
				in.close();
			}
		} catch (Exception e) {
			System.out.println("InputStream construction failed");
		}
	}
}

