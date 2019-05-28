package com.sly.demo.thinkinjava.chapter10.parcel7;

import com.sly.demo.thinkinjava.chapter10.parcel4.Contents;

/**
 * 一个实现了接口的匿名类
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel7 {
	public Contents contents() {
		return new Contents(){
			private int i = 15;

			@Override
			public int value() {
				return i;
			}
		};
	}
	
	public static void main(String[] args) {
		Parcel7 parcel7 = new Parcel7();
		Contents contents = parcel7.contents();
		System.out.println(contents.value());
	}
}
