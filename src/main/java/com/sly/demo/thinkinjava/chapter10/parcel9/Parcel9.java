package com.sly.demo.thinkinjava.chapter10.parcel9;

import com.sly.demo.thinkinjava.chapter10.parcel4.Destination;

/**
 * 一个匿名类，它执行字段的初始化
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel9 {
	/**
	 * “如果定义一个匿名内部类，并且希望它使用一个在其外部定义的对象，那么编译器会要求其参数引用是final的”，
	 *  如果你使用jdk1.8版本不写final是不会报错的，因为编译器默认为final。
	 * @param dest
	 * @return
	 * @author sly
	 * @time 2019年5月30日
	 */
	public Destination destination(final String dest) {
		return new Destination() {
			private String lable = dest;
			@Override
			public String readLabel() {
				return lable;
			}
		};
	}
	
	public static void main(String[] args) {
		Parcel9 parcel9 = new Parcel9();
		Destination destination = parcel9.destination("海王星");
		System.out.println(destination.readLabel());
	}
}
