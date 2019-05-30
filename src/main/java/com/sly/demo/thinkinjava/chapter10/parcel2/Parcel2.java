package com.sly.demo.thinkinjava.chapter10.parcel2;

/**
 * 返回一个内部类的引用
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel2 {
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
	
	public Destination to(String dest) {
		return new Destination(dest);
	}
	
	public Contents contents() {
		return new Contents();
	}
	
	public void ship(String dest) {
		Contents contents = contents();
		Destination destination = to(dest);
		System.out.println(contents.value() + ":" + destination.readLabel());
	}
	
	public static void main(String[] args) {
		Parcel2 parcel2 = new Parcel2();
		parcel2.ship("木星");
		
		Destination destination = parcel2.to("土星");
		System.out.println(destination.readLabel());
	}
}
