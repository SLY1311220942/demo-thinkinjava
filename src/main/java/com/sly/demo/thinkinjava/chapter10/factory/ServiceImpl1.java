package com.sly.demo.thinkinjava.chapter10.factory;

/**
 * Service具体实现一
 * 
 * @author sly
 * @time 2019年5月31日
 */
public class ServiceImpl1 implements Service {

	private ServiceImpl1() {

	}

	@Override
	public void methodOne() {
		System.out.println("ServiceImpl1:methodOne");
	}

	@Override
	public void methodTwo() {
		System.out.println("ServiceImpl1:methodTwo");
	}

	public static ServiceFactory factory = new ServiceFactory() {

		@Override
		public Service getService() {
			return new ServiceImpl1();
		}

	};

}
