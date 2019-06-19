package com.sly.demo.thinkinjava.chapter11.queue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author sly
 * @time 2019年6月19日
 */
public class PriorityQueueDemo {
	
	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		Random random = new Random(47);
		for (int i = 0; i < 10; i++) {
			queue.offer(random.nextInt(i + 10));
		}
		QueueDemo.printQueue(queue);
		
		List<Integer> ints = Arrays.asList(45,1,52,4,8,3,4,69,5,15,13,41,58,78,1,2);
		PriorityQueue<Integer> queue2 = new PriorityQueue<>(ints);
		QueueDemo.printQueue(queue2);
		
		String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
		List<String> strings = Arrays.asList(fact.split(" "));
		PriorityQueue<String> queue3 = new PriorityQueue<>(strings);
		QueueDemo.printQueue(queue3);
		
		PriorityQueue<Character> queue4 = new PriorityQueue<>();
		for (Character character : fact.toCharArray()) {
			queue4.offer(character);
		}
		QueueDemo.printQueue(queue4);
		
		
		Set<Character> charSet = new HashSet<>();
		for (Character character : fact.toCharArray()) {
			charSet.add(character);
		}
		PriorityQueue<Character> queue5 = new PriorityQueue<>(charSet);
		QueueDemo.printQueue(queue5);
		
	}
}

