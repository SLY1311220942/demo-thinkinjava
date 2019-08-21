package com.sly.demo.thinkinjava.chapter15.tuple;

/**
 * 
 * @author sly
 * @time 2019年8月21日
 */
public class TupleTest {
	

	public static void main(String[] args) {

//		TwoTuple<User, Group> tuple = new TwoTuple<User, Group>(new User("大毛"), new Group("一组"));
//		System.out.println(tuple);
//		System.out.println("=================");
//		User first = tuple.first;
//		Group second = tuple.second;
//		first.setName("二毛");
//		second.setName("二组");
//		System.out.println("first:" + first + " second:" + second);
//		System.out.println("=================");
//		System.out.println(tuple);
		

		TwoTuple<String, String> tuple = new TwoTuple<String, String>("大毛", "一组");
		System.out.println(tuple);
		System.out.println("=================");
		String f = "二毛";
		String s = "二组";
		String first = tuple.first;
		String second = tuple.second;
		first = f;
		second = s;
		System.out.println("first:" + first + " second:" + second);
		System.out.println(tuple);
	}
}

class User {
	public String name;

	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[user:" + name + "]";
	}
}

class Group {
	public String name;

	public Group(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[group:" + name + "]";
	}

}
