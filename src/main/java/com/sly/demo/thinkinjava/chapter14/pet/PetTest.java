package com.sly.demo.thinkinjava.chapter14.pet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

class Pet {

}

class Cat extends Pet {

}

class Rat extends Pet {

}

class Dog extends Pet {

}

class Pug extends Pet {

}

public class PetTest {
	public static void main(String[] args) throws Exception {
		Map<String, Integer> countMap = new HashMap<>();
		Class<?> basetype = Pet.class;
		List<Pet> create = create();
		for (Pet pet : create) {
			if (!basetype.isAssignableFrom(pet.getClass())) {
				throw new RuntimeException();
			}
			count(pet.getClass().getSimpleName(), countMap);
		}
		System.out.println(countMap);
	}

	public static List<Pet> create() throws Exception {
		List<Pet> pets = new ArrayList<>();
		List<Class<? extends Pet>> classes = Arrays.asList(Cat.class, Rat.class, Dog.class, Pug.class);
		for (Class<? extends Pet> class1 : classes) {
			Pet newInstance = class1.newInstance();
			pets.add(newInstance);

		}
		return pets;
	}

	public static void count(String type, Map<String, Integer> countMap) {
		if(countMap.get(type) == null) {
			countMap.put(type, 1);
		}else {
			countMap.put(type, countMap.get(type) + 1);
		}
	}
}
