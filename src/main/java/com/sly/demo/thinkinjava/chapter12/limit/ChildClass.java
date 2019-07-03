package com.sly.demo.thinkinjava.chapter12.limit;

/**
 * 
 * @author sly
 * @time 2019年7月3日
 */
public class ChildClass extends ParentClass{
	
	/*@Override
	public void f() throws TwoException  {
		throw new TwoException();
	}*/
	
	@Override
	public void f() throws ChildOneException  {
		throw new ChildOneException();
	}
}

