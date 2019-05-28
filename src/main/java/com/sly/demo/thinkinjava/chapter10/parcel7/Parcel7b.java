package com.sly.demo.thinkinjava.chapter10.parcel7;

import com.sly.demo.thinkinjava.chapter10.parcel4.Contents;

/**
 * 创建一个继承自接口的匿名类对象
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel7b {
	class PContents implements Contents {
		private int i = 16;

		@Override
		public int value() {
			return i;
		}
	}

	public Contents contents() {
		return new PContents();
	}

	public static void main(String[] args) {
		Parcel7b parcel7b = new Parcel7b();
		Contents contents = parcel7b.contents();
		System.out.println(contents.value());
	}
}
