package com.application.person.model;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	private String responseBody;

	public ErrorDetails(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ErrorDetails(Date timestamp, String message, String details, String responseBody) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.responseBody = responseBody;
	}

}