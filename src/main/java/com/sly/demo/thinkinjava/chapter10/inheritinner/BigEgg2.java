package com.sly.demo.thinkinjava.chapter10.inheritinner;

class Egg2 {
	protected class Yolk {

		public Yolk() {
			System.out.println("Egg2.Yolk()");
		}

		public void f() {
			System.out.println("Egg2.f()");
		}
	}

	private Yolk yolk = new Yolk();

	public Egg2() {
		System.out.println("New Egg2()");
	}

	public void insertYolk(Yolk yolk) {
		this.yolk = yolk;
	}

	public void g() {
		yolk.f();
	}
}

public class BigEgg2 extends Egg2 {
	public class Yolk extends Egg2.Yolk {
		public Yolk() {
			System.out.println("BigEgg2.Yolk()");
		}

		public void f() {
			System.out.println("BigEgg2.f()");
		}
	}

	public BigEgg2() {
		insertYolk(new Yolk());
	}

	public static void main(String[] args) {
		BigEgg2 bigEgg2 = new BigEgg2();
		bigEgg2.g();
	}
}
