package com.sly.demo.thinkinjava.chapter10.parcel8;

/**
 * 一个匿名类，它扩展了有非默认构造器的类
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel8 {
	
	/**
	 * Wapping只是具有具体实现的普通类，但它还是被其导出类当作公共“接口”使用
	 * @param x
	 * @return
	 * @author sly
	 * @time 2019年5月28日
	 */
	public Wapping wapping(int x) {
		return new Wapping(x) {
			@Override
			public int value() {
				return super.value() * 47;
			}
		};
	}
	
	public static void main(String[] args) {
		Parcel8 parcel8 = new Parcel8();
		Wapping wapping = parcel8.wapping(10);
		System.out.println(wapping.value());
	}

}
