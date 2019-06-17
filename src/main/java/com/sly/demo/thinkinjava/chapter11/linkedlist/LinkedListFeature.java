package com.sly.demo.thinkinjava.chapter11.linkedlist;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * @author sly
 * @time 2019年6月17日
 */
public class LinkedListFeature {
	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<>(Arrays.asList("one","two","three","four","five"));
		System.out.println(linkedList);
		System.out.println("linkedList.getFirst():" + linkedList.getFirst());
		System.out.println("linkedList.element():" + linkedList.element());
		System.out.println("linkedList.peek():" + linkedList.peek());
		System.out.println("linkedList.remove():" + linkedList.remove());
		System.out.println("linkedList.removeFirst():" + linkedList.removeFirst());
		System.out.println("linkedList.poll():" + linkedList.poll());
		System.out.println(linkedList);
		linkedList.addFirst("addFirst");
		System.out.println("After addFirst():" + linkedList);
		linkedList.offer("offer");
		System.out.println("After offer():" + linkedList);
		linkedList.add("add");
		System.out.println("After add():" + linkedList);
		linkedList.addLast("addLast");
		System.out.println("After addLast():" + linkedList);
		System.out.println("print removeLast():" + linkedList.removeLast());
	}
}

