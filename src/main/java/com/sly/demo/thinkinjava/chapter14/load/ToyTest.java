package com.sly.demo.thinkinjava.chapter14.load;

class Toy {

}

class FancyToy extends Toy {

}

public class ToyTest {
	public static void main(String[] args) throws Exception {
		Class<FancyToy> ftClass = FancyToy.class;
		FancyToy fancyToy = ftClass.newInstance();
		Class<? super FancyToy> up = ftClass.getSuperclass();
		// 编译不通过
		// Class<Toy> superclass = ftClass.getSuperclass();
		Object newInstance = up.newInstance();
		
		
	}
}
