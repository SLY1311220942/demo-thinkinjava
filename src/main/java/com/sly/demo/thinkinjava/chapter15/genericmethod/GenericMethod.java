package com.sly.demo.thinkinjava.chapter15.genericmethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sly.demo.thinkinjava.chapter14.pet.PetTest;


public class GenericMethod {
	public <T> void f(T t) {
		System.out.println(t.getClass().getSimpleName());
	}
	
	public void f2(Map<String, List<? extends PetTest>> map) {
		
	}
	
	public void f3(String...names) {
		for (String name : names) {
			System.out.println(name);
		}
	}
	
	public static void main(String[] args) {
		GenericMethod genericMethod = new GenericMethod();
		genericMethod.f(1);
		genericMethod.f("");
		genericMethod.f('1');
		genericMethod.f(true);
		
		Map<String, List<? extends PetTest>> map = new HashMap<String, List<? extends PetTest>>();
		
		System.out.println(map);
		
		genericMethod.f2(GenericMethod.map());
	}
	
	
	
	public static <K,V> Map<K,V> map(){
		//return new HashMap<K,V>();
		return new HashMap<>();
	}
	
	
}
