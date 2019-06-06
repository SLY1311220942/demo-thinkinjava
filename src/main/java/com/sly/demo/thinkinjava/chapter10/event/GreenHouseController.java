package com.sly.demo.thinkinjava.chapter10.event;

/**
 * 
 * @author sly
 * @time 2019年6月6日
 */
public class GreenHouseController {
	
	public static void main(String[] args) throws InterruptedException {
		GreenHouseControls controls = new GreenHouseControls();
		controls.addEvent(controls.new Bell(900));
		Event[] eventList = {
			controls.new ThermostatNight(0),
			controls.new lightOn(200),
			controls.new lightOff(400),
			controls.new waterOn(600),
			controls.new waterOff(800),
			controls.new ThermostatDay(1400)
		};
		controls.addEvent(controls.new Restart(2000, eventList));
		
		if(args.length == 1) {
			controls.addEvent(new GreenHouseControls.Terminate(new Integer(args[0])));
			
		}
		controls.run();
	}
}
