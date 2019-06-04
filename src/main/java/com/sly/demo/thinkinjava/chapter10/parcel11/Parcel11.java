package com.sly.demo.thinkinjava.chapter10.parcel11;

import com.sly.demo.thinkinjava.chapter10.parcel4.Contents;
import com.sly.demo.thinkinjava.chapter10.parcel4.Destination;

/**
 * 
 * @author sly
 * @time 2019年5月31日
 */
public class Parcel11 {
	private static class ParcelContents implements Contents {
		private int i = 11;

		@Override
		public int value() {
			return i;
		}

	}

	protected static class ParcelDestination implements Destination {
		private String lable;

		public ParcelDestination(String whereTo) {
			lable = whereTo;
		}

		@Override
		public String readLabel() {
			return lable;
		}

		// 嵌套类可以包含其它静态元素
		static int x = 10;

		public static void f() {
			System.out.println(x);
		};

		static class AnotherLevel {
			static int x = 17;

			public static void f() {
				System.out.println(x);
			};

		}
		
	}
	
	public static Destination destination(String s) {
		return new ParcelDestination(s);
	}
	
	public static Contents contents() {
		return new ParcelContents();
	}
	
	public static void main(String[] args) {
		Destination destination = destination("冥王星");
		Contents contents = contents();
		System.out.println(contents.value());
		System.out.println(destination.readLabel());
		
	}
}
