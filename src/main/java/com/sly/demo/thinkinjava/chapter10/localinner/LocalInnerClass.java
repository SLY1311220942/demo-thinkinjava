package com.sly.demo.thinkinjava.chapter10.localinner;

/**
 * 
 * @author sly
 * @time 2019年6月14日
 */
public class LocalInnerClass {
	private int count = 0;
	
	/**
	 * java 8 可以不加final
	 * @param name
	 * @return
	 * @author sly
	 * @time 2019年6月14日
	 */
	Counter getCounter(String name) {
		class LocalCounter implements Counter{
			public LocalCounter() {
				System.out.println("LocalCounter()");
			}
			
			@Override
			public int next() {
				System.out.println(name);
				new InnerLocalCounter();
				return count++;
			}
			
			class InnerLocalCounter implements Counter{

				@Override
				public int next() {
					// TODO Auto-generated method stub
					return 0;
				}
	
			}
		}
		
		
		return new LocalCounter();
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @author sly
	 * @time 2019年6月14日
	 */
	Counter getCounter2(String name) {
		return new Counter() {
			{
				System.out.println("Counter()");
			}
			
			@Override
			public int next() {
				System.out.println(name);
				return count++;
			}
		};
	}
	
	public static void main(String[] args) {
		LocalInnerClass localInnerClass = new LocalInnerClass();
		Counter c1 = localInnerClass.getCounter("local inner");
		Counter c2 = localInnerClass.getCounter2("anonymous inner");
		for (int i = 0; i < 5; i++) {
			System.out.println(c1.next());
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(c2.next());
		}
	}
}

