package com.sly.demo.thinkinjava.chapter10.parcel4;

/**
 * 
 * @author sly
 * @time 2019年5月28日
 */
public class Parcel4 {
	private class PContents implements Contents {
		private int i = 11;

		@Override
		public int value() {
			return i;
		}

	}

	protected class PDestination implements Destination {
		private String label;

		private PDestination(String dest) {
			label = dest;
		}

		@Override
		public String readLabel() {
			return label;
		}
	}

	public Contents contents() {
		return new PContents();
	}

	public Destination destination(String dest) {
		return new PDestination(dest);
	}
}
