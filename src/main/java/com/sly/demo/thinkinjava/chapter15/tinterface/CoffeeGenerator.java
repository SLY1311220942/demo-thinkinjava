package com.sly.demo.thinkinjava.chapter15.tinterface;

import java.util.Iterator;
import java.util.Random;

/**
 * 
 * @author sly
 * @time 2019年8月23日
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
	private Class[] types = { Latter.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class };

	private Random random = new Random(47);

	private int size = 0;

	public CoffeeGenerator() {
	}

	public CoffeeGenerator(int size) {
		this.size = size;
	}

	@Override
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();
	}

	@Override
	public Coffee next() {
		try {
			return (Coffee) types[random.nextInt(types.length)].newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class CoffeeIterator implements Iterator<Coffee> {
		int count = size;

		@Override
		public boolean hasNext() {

			return count > 0;
		}

		@Override
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}

	}
	
	
	public static void main(String[] args) {
		CoffeeGenerator coffeeGenerator = new CoffeeGenerator();
		for (int i = 0; i < 5; i++) {
			System.out.println(coffeeGenerator.next());
		}
		
		for (Coffee coffee : new CoffeeGenerator(5)) {
			System.out.println(coffee);
		}
	}

}
