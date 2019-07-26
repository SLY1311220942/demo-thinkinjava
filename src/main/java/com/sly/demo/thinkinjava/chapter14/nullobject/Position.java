package com.sly.demo.thinkinjava.chapter14.nullobject;

/**
 * 
 * @author sly
 * @time 2019年7月26日
 */
public class Position {
	private String title;
	private Person person;
	
	public Position(String jobTitle,Person employee) {
		this.title = jobTitle;
		this.person = employee;
		if(person == null) {
			this.person = Person.NULL;
		}
	}
	
	public Position(String jobTitle) {
		this.title = jobTitle;
		this.person = Person.NULL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
		if(person == null) {
			this.person = Person.NULL;
		}
	}
	
	@Override
	public String toString() {
		return "Position: " + title + " " + person;
	}
	
}

