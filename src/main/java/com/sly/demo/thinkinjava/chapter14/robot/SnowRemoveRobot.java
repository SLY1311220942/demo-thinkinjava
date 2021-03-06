package com.sly.demo.thinkinjava.chapter14.robot;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author sly
 * @time 2019年7月30日
 */
public class SnowRemoveRobot implements Robot{
	private String name;
	
	public SnowRemoveRobot(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String model() {
		// TODO Auto-generated method stub
		return "SnowRobot Series 11";
	}

	@Override
	public List<Operation> Operations() {
		
		return Arrays.asList(
			new Operation() {
				@Override
				public String description() {
					return name + " can shovel snow";
				}
				@Override
				public void command() {
					System.out.println(name + " shoveling snow");
				}
			},
			new Operation() {
				@Override
				public String description() {
					return name + " can chip ice";
				}
				@Override
				public void command() {
					System.out.println(name + " chipping ice");
				}
			},
			new Operation() {
				@Override
				public String description() {
					return name + " can clear the roof";
				}
				@Override
				public void command() {
					System.out.println(name + " clearing roof");
				}
			}
		);
	}
	
	public static void main(String[] args) {
		Robot.Test.test(new SnowRemoveRobot("Slusher"));
	}

}

