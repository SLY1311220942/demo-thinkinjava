package com.sly.demo.thinkinjava.chapter12.catchdemo;

import java.util.logging.Logger;

/**
 * 
 * @author sly
 * @time 2019年7月2日
 */
public class DemoCatch {
	private static final Logger LOGGER = Logger.getLogger("DemoCatch");
	
	public static void main(String[] args) throws TypeException {
		/*try {*/
			throw new TypeException(15,"dasd");
		/*} catch (TypeOneException e) {
			System.out.println("TypeOneException");
		} catch (TypeException e) {
			System.out.println("TypeException");
		} catch (Exception e) {
			System.out.println("Exception");
		}*/
	}
}

