package com.sly.demo.thinkinjava.chapter10.event;

/**
 * 温室控制
 * 
 * @author sly
 * @time 2019年6月6日
 */
public class GreenHouseControls extends Controller {
	private boolean light = false;

	// 开灯
	public class lightOn extends Event {
		public lightOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			light = true;
		}

		public String toString() {
			return "Light is on";
		}
	}

	// 关灯
	public class lightOff extends Event {
		public lightOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			light = false;
		}

		public String toString() {
			return "Light is off";
		}
	}

	private boolean water = false;

	// 开水
	public class waterOn extends Event {
		public waterOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			water = true;
		}

		public String toString() {
			return "Water is on";
		}
	}

	// 关水
	public class waterOff extends Event {
		public waterOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			water = true;
		}

		public String toString() {
			return "Water is off";
		}
	}

	private String thermostat = "Day";

	// 温度调节白天
	public class ThermostatDay extends Event {
		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			thermostat = "Day";
		}

		public String toString() {
			return "Thermostat on day setting";
		}
	}

	// 温度调节夜晚
	public class ThermostatNight extends Event {
		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			thermostat = "Night";
		}

		public String toString() {
			return "Thermostat on night setting";
		}
	}

	// 响铃
	public class Bell extends Event {
		public Bell(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			addEvent(new Bell(delayTime));
		}

		public String toString() {
			return "Bing!";
		}

	}

	public class Restart extends Event {
		private Event[] eventList;

		public Restart(long delayTime, Event[] eventList) {
			super(delayTime);
			this.eventList = eventList;
			for (Event event : eventList) {
				addEvent(event);
			}
		}

		@Override
		public void action() {
			for (Event event : eventList) {
				event.action();
				addEvent(event);
			}

			start();
			addEvent(this);
		}

		public String toString() {
			return "Restarting system";
		}
	}

	public static class Terminate extends Event {

		public Terminate(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			System.exit(0);
		}

		public String toString() {
			return "Terminating";
		}
	}
}
