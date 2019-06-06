package com.sly.demo.thinkinjava.chapter10.event;

/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public abstract class Event {
	private long eventTime;
	protected final long delayTime;
	
	public Event(long delayTime) {
		this.delayTime = delayTime;
	}

	public void start() {
		eventTime = System.nanoTime() + delayTime;
	}

	public boolean ready() {
		return System.nanoTime() >= eventTime;
	}

	public abstract void action();
}
