package com.sly.demo.thinkinjava.chapter10.classininterface;

/**
 * 接口嵌套类
 * 
 * @author sly 
 * @time 2019年6月4日
 */
public interface ClassInInterface {
	void how();
	class TestClassInInterface implements ClassInInterface{

		@Override
		public void how() {
			System.out.println("TestClassInInterface");
		}
		
		public static void main(String[] args) {
			System.out.println("TestClassInInterface");
		}
	}
	
	
}
