package com.sly.demo.thinkinjava.chapter14.load;

class Candy {
	static {
		System.out.println("Loading Candy");
	}
}

class Gum {
	static {
		System.out.println("Loading Gum");
	}
}

class Cookie {
	static {
		System.out.println("Loading Cookie");
	}
}


public class SweetShop {
	public static void main(String[] args) throws ClassNotFoundException {
		new Candy();
		System.out.println("-------------------");
		Class.forName("com.sly.demo.thinkinjava.chapter14.load.Gum");
		System.out.println("-------------------");
		new Cookie();
		System.out.println("-------------------");
	}
}
