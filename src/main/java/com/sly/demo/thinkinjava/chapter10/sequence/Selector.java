package com.sly.demo.thinkinjava.chapter10.sequence;

/**
 * 选择器接口
 * 
 * @author sly
 * @time 2019年5月28日
 */
public interface Selector {
	boolean end();

	Object current();

	void next();
}
