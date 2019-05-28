package com.sly.demo.thinkinjava.chapter10.test;

import org.junit.Test;

import com.sly.demo.thinkinjava.chapter10.dotthis.DotThis;
import com.sly.demo.thinkinjava.chapter10.parcel4.Contents;
import com.sly.demo.thinkinjava.chapter10.parcel4.Destination;
import com.sly.demo.thinkinjava.chapter10.parcel4.Parcel4;

/**
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class DemoTest {

	@Test
	public void test_01() {
		DotThis dotThis = new DotThis();
		DotThis.Inner inner = dotThis.inner();
		inner.outer().f();
	}
	
	@Test
	public void test_02() {
		Parcel4 parcel4 = new Parcel4();
		Contents contents = parcel4.contents();
		Destination destination = parcel4.destination("金星");
		System.out.println(contents.value() + ":" + destination.readLabel());
	}
}

