package com.sly.demo.thinkinjava.chapter14.robot;


import java.util.List;

import com.sly.demo.thinkinjava.chapter14.nullobject.Null;

/**
 * 
 * @author sly
 * @time 2019年7月30日
 */
public interface Robot {
	String name();
	String model();
	List<Operation> Operations();
	
	class Test {
		public static void test(Robot r) {
			if(r instanceof Null) {
				System.out.println("[Null Robot]");
			}
			System.out.println("Robot name: " + r.name());
			System.out.println("Robot model: " + r.model());
			for (Operation operation : r.Operations()) {
				System.out.println(operation.description());
				operation.command();
			}
		}
	}
}

