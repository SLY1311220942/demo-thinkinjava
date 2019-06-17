package com.sly.demo.thinkinjava.chapter11.stack;

import java.util.LinkedList;

/**
 * 
 * @author sly
 * @time 2019年6月17日
 */
public class Stack<T> {
	private LinkedList<T> storage = new LinkedList<>();
	public void push(T v) {
		storage.addFirst(v);;
	}
	public T peek() {
		return storage.getFirst();
	}
	public T pop() {
		return storage.removeFirst();
	}
	public boolean empty() {
		return storage.isEmpty();
	}
	public String toString() {
		return storage.toString();
	}
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		for (String s : "my dog has fleas".split(" ")) {
			stack.push(s);
		}
		
		while (!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
	}
}
