package com.sly.demo.thinkinjava.chapter10.event;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public class Controller {
	private List<Event> eventList = new ArrayList<>();
	public void addEvent(Event event) {
		eventList.add(event);
	}
	
	public void run() {
		while(eventList.size() > 0) {
			for (Event event : new ArrayList<>(eventList)) {
				if(event.ready()) {
					System.out.println(event);
					event.action();
					eventList.remove(event);
				}
			}
		}
	}
}

