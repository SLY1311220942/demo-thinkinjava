package com.sly.demo.thinkinjava.chapter10.sequence;

/**
 * 
 * @author sly
 * @time 2019年5月28日
 */
public interface Selector {
	boolean end();
	Object current();
	void next();
}

