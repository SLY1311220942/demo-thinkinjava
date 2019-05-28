package com.sly.demo.thinkinjava.chapter10.parcel5;

import com.sly.demo.thinkinjava.chapter10.parcel4.Destination;

/**
 * 一个定义在方法中的类
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel5 {

	public Destination destination(String dest) {
		class PDestination implements Destination {
			private String label;

			private PDestination(String dest) {
				label = dest;
			}

			@Override
			public String readLabel() {
				return label;
			}
		}
		
		return new PDestination(dest);
	}
	
	public static void main(String[] args) {
		Parcel5 parcel5 = new Parcel5();
		Destination destination = parcel5.destination("土星");
		System.out.println(destination.readLabel());
	}
}
