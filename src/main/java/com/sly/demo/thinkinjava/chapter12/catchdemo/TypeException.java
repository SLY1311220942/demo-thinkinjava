package com.sly.demo.thinkinjava.chapter12.catchdemo;

/**
 * 
 * @author sly
 * @time 2019年7月2日
 */
public class TypeException extends Exception {
	
	private static final long serialVersionUID = -2685397076276132626L;
	
	private Integer status;
	private String asd;

	public TypeException() {

	}

	public TypeException(Integer status, String message) {
		this.status = status;
		this.asd = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAsd() {
		return asd;
	}

	public void setAsd(String asd) {
		this.asd = asd;
	}

	
	
	
}
