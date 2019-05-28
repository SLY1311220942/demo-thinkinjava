package com.sly.demo.thinkinjava.chapter10.parcel6;

/**
 * 一个定义在作用域内的类，此作用域在方法的内部
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel6 {
	
	private void internalTracking(boolean b) {
		if(b) {
			class TrackingShip{
				private String id;
				TrackingShip(String s){
					id = s;
				}
				String getShip() {
					return id;
				}
			}
			TrackingShip ship = new TrackingShip("ship");
			String s = ship.getShip();
			System.out.println(s);
		}
	}
	
	public void track() {
		internalTracking(true);
	}
	
	public static void main(String[] args) {
		Parcel6 parcel6 = new Parcel6();
		parcel6.track();
	}
}
