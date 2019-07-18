package com.sly.demo.thinkinjava.chapter14.load;

import java.util.Random;

class InitTable {
	static final int staticFinal = 47;
	static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
	static {
		System.out.println("Initializing InitTable");
	}
}

class InitTable2 {
	static final int staticNonFinal = 147;
	static {
		System.out.println("Initializing InitTable2");
	}
}

class InitTable3 {
	static final int staticNonFinal = 74;
	static {
		System.out.println("Initializing InitTable3");
	}
}

public class ClassInitialization {
	public static Random rand = new Random(47);
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException {
		Class<InitTable> initTable= InitTable.class;
		System.out.println("-------------after create InitTable ref------------");
		System.out.println(InitTable.staticFinal);
		System.out.println(InitTable.staticFinal2);
		System.out.println(InitTable2.staticNonFinal);
		Class<?> initTable3 = Class.forName("com.sly.demo.thinkinjava.chapter14.load.InitTable3");
		System.out.println("-------------after create InitTable3 ref------------");
		System.out.println(InitTable3.staticNonFinal);
	}
}
