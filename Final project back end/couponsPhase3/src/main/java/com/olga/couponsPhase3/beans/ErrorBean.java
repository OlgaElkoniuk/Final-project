package com.olga.couponsPhase3.beans;

import com.olga.couponsPhase3.enums.ErrorType;

public class ErrorBean {

	// property

	private int statusCode;
	private ErrorType internalMessage;
	private String externalMessage;


	public ErrorBean(int statusCode, ErrorType internalMessage, String externalMessage) {
		this();
		this.statusCode = statusCode;
		this.internalMessage = internalMessage;
		this.externalMessage = externalMessage;
	}


	public ErrorBean() {
		super();
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public ErrorType getInternalMessage() {
		return internalMessage;
	}


	public void setInternalMessage(ErrorType internalMessage) {
		this.internalMessage = internalMessage;
	}


	public String getExternalMessage() {
		return externalMessage;
	}


	public void setExternalMessage(String externalMessage) {
		this.externalMessage = externalMessage;
	}

	@Override
	public String toString() {
		return "ErrorBean [statusCode=" + getStatusCode() + ", internalMessage=" + getInternalMessage()
				+ ", externalMessage=" + getExternalMessage() + "]";
	}

}