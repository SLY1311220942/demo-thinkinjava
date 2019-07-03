package com.sly.demo.thinkinjava.chapter12.lostexception;

/**
 * 
 * @author sly
 * @time 2019年7月3日
 */
public class VeryImportantException extends Exception {

	private static final long serialVersionUID = 6032662186164064388L;
	
	@Override
	public String toString() {
		return "A very important exception";
	}
}

