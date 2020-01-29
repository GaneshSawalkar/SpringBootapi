package com.bridgelabz.user.responce;

public class Responce {
	private String StringException;
	private int statusCode;

	public Responce(int statusCode, String stringException) {
		this.StringException = stringException;
		this.statusCode = statusCode;
	}

	public Responce() {
	}

	public String getStringException() {
		return StringException;
	}

	public void setStringException(String stringException) {
		StringException = stringException;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
