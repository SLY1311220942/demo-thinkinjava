package com.sly.demo.thinkinjava.chapter14.nullobject;

import java.util.ArrayList;

/**
 * 
 * @author sly
 * @time 2019年7月26日
 */
public class Staff extends ArrayList<Position> {

	private static final long serialVersionUID = 1L;

	public void add(String title,Person person) {
		add(new Position(title, person));
	}
	
	public void add(String...titles) {
		for (String title : titles) {
			add(new Position(title));
		}
	}
	
	public Staff(String...titles) {
		add(titles);
	}
	
	public boolean positionAvailable(String title) {
		for (Position position : this) {
			if(position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
				return true;
			}
		}
		return false;
	}
	
	public void fillPosition(String title,Person hire) {
		for (Position position : this) {
			if(position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
				position.setPerson(hire);
				return ;
			}
		}
		throw new RuntimeException("Position:" + title + " not avaliable");
	}
	
	public static void main(String[] args) {
		Staff staff = new Staff("Persident","CTO","Marketing Manager","Product Manager","Project Lead",
				"Software Engineer","Software Engineer","Software Engineer","Software Engineer",
				"Test Engineer","Technical Writer");
		staff.fillPosition("Persident", new Person("Me","Last","The top lonely At"));
		staff.fillPosition("Project Lead", new Person("Janet","Planner","The Burbs"));
		if(staff.positionAvailable("Software Engineer")) {
			staff.fillPosition("Software Engineer", new Person("Bob","Coder","Bright Light City"));
		}
		System.out.println(staff);
	}
}

