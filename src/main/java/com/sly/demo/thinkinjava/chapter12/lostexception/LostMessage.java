package com.sly.demo.thinkinjava.chapter12.lostexception;

/**
 * 
 * @author sly
 * @time 2019年7月3日
 */
public class LostMessage {
	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}
	
	void dispose() throws HoHumException {
		throw new HoHumException();
	}
	
	
	public static void main(String[] args) {
		try {
			LostMessage lostMessage = new LostMessage();
			try {
				lostMessage.f();
			} finally {
				lostMessage.dispose();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

