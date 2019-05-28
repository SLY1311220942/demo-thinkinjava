package com.sly.demo.thinkinjava.chapter10.parcel1;

/**
 * 创建内部类
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel1 {

	class Contents {
		private int i = 11;

		public int value() {
			return i;
		}
	}

	class Destination {
		private String label;

		public Destination(String whereTo) {
			this.label = whereTo;
		}

		String readLabel() {
			return label;
		}
	}
	
	public void ship(String dest) {
		Contents contents = new Contents();
		Destination destination = new Destination(dest);
		System.out.println(contents.value() + ":" + destination.readLabel());
	}
	
	public static void main(String[] args) {
		Parcel1 parcel1 = new Parcel1();
		parcel1.ship("火星");
	}
}
