package com.sly.demo.thinkinjava.chapter14.registryfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Part {
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();
	static {
		partFactories.add(new FuelFilter.Factory());
		partFactories.add(new AirFilter.Factory());
		partFactories.add(new FanBelt.Factory());
		partFactories.add(new GeneratorBelt.Factory());
	}
	
	private static Random rand = new Random(47);
	
	public static Part createRandom() {
		int n = rand.nextInt(partFactories.size());
		return partFactories.get(n).create();
	}
}

class Filter extends Part {

}

class FuelFilter extends Filter {
	public static class Factory implements com.sly.demo.thinkinjava.chapter14.registryfactory.Factory<FuelFilter> {
		@Override
		public FuelFilter create() {
			return new FuelFilter();
		}
	}
}

class AirFilter extends Filter {
	public static class Factory implements com.sly.demo.thinkinjava.chapter14.registryfactory.Factory<AirFilter> {
		@Override
		public AirFilter create() {
			return new AirFilter();
		}
	}
}

class Belt extends Part {

}

class FanBelt extends Belt {
	public static class Factory implements com.sly.demo.thinkinjava.chapter14.registryfactory.Factory<FanBelt> {
		@Override
		public FanBelt create() {
			return new FanBelt();
		}
	}
}

class GeneratorBelt extends Belt {
	public static class Factory implements com.sly.demo.thinkinjava.chapter14.registryfactory.Factory<GeneratorBelt> {
		@Override
		public GeneratorBelt create() {
			return new GeneratorBelt();
		}
	}
}

public class RegistryFactory {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(Part.createRandom());
		}
	}
}
