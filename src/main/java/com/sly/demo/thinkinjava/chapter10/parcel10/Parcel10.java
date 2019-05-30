package com.sly.demo.thinkinjava.chapter10.parcel10;

/**
 * 一个匿名类，它通过实例初始化实现构造器（匿名类不可能有命名构造器）
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel10 {

	/**
	 * 这里的i不需要是final的，因为他是传递给基类使用而不是在匿名内部类中使用。
	 * 
	 * @param i
	 * @return
	 * @author sly
	 * @time 2019年5月30日
	 */
	public Base getBase(int i) {
		return new Base(i) {

			@Override
			void f() {
				System.out.println("I'am f();");
			}
		};
	}

	public static void main(String[] args) {
		Parcel10 parcel10 = new Parcel10();
		Base base = parcel10.getBase(45);
		base.f();
	}
}
