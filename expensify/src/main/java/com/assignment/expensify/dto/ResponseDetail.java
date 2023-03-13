package com.assignment.expensify.dto;

public class ResponseDetail {
	
	String responseMessage;
	String responseCode;
	
	public ResponseDetail(String responseMessage,String responseCode) {
		this.responseMessage=responseMessage;
		this.responseCode=responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	

}
