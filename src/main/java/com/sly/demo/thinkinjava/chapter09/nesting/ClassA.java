package com.sly.demo.thinkinjava.chapter09.nesting;

/**
 * 
 * @author sly
 * @time 2019年5月17日
 */
public class ClassA {
	interface InterfaceB {
		void methodF();
	}

	public class ClassBone implements InterfaceB {
		@Override
		public void methodF() {
		}
	}

	public class ClassBtow implements InterfaceB {
		@Override
		public void methodF() {
		}
	}

	interface InterfaceC {
		void methodF();
	}

	class ClassCone implements InterfaceC {
		@Override
		public void methodF() {
		}
	}

	private class ClassCtwo implements InterfaceC {
		@Override
		public void methodF() {
		}
	}

	private interface InterfaceD {
		public void methodF();
	}

	private class ClassDone implements InterfaceD {
		@Override
		public void methodF() {
		}
	}
	
	public class ClassDtwo implements InterfaceD {
		@Override
		public void methodF() {
		}
	}
}
