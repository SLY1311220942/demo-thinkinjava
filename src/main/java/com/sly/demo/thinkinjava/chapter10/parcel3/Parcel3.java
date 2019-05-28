package com.sly.demo.thinkinjava.chapter10.parcel3;

/**
 * .new在parcel上的应用
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel3 {
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

	public static void main(String[] args) {
		Parcel3 parcel3 = new Parcel3();
		Parcel3.Contents contents = parcel3.new Contents();
		Parcel3.Destination destination = parcel3.new Destination("水星");
		System.out.println(contents.value() + ":" + destination.readLabel());
	}
}
