package com.sly.demo.thinkinjava.chapter11.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 
 * @author sly
 * @time 2019年6月19日
 */
public class QueueDemo {
	public static void printQueue(Queue queue) {
		while (queue.peek() != null) {
			System.out.print(queue.remove() + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		Random random = new Random(47);
		for (int i = 0; i < 10; i++) {
			queue.offer(random.nextInt(i + 10));
		}
		printQueue(queue);
		
		Queue<Character> queue2 = new LinkedList<>();
		for (Character c : "hello world".toCharArray()) {
			queue2.offer(c);
		}
		printQueue(queue2);
	}
}

