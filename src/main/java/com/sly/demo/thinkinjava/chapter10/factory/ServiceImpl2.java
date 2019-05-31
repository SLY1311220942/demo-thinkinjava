package com.sly.demo.thinkinjava.chapter10.factory;

/**
 * Service具体实现二
 * 
 * @author sly
 * @time 2019年5月31日
 */
public class ServiceImpl2 implements Service {

	private ServiceImpl2() {

	}

	@Override
	public void methodOne() {
		System.out.println("ServiceImpl2:methodOne");
	}

	@Override
	public void methodTwo() {
		System.out.println("ServiceImpl2:methodTwo");
	}

	public static ServiceFactory factory = new ServiceFactory() {

		@Override
		public Service getService() {
			return new ServiceImpl2();
		}
	};

}
