package com.sly.demo.thinkinjava.chapter10.factory;

/**
 * 
 * @author sly
 * @time 2019年5月31日
 */
public class Factories {
	public static void serviceCustomer(ServiceFactory factory) {
		Service service = factory.getService();
		service.methodOne();
		service.methodTwo();
	}
	
	public static void main(String[] args) {
		serviceCustomer(ServiceImpl1.factory);
		System.out.println("==========================");
		serviceCustomer(ServiceImpl2.factory);
	}
}
