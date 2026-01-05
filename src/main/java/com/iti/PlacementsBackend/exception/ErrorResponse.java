package com.iti.PlacementsBackend.exception;

public class ErrorResponse {
	private String timestamp;
	private Integer httpStatus;
    private String message;
    private String details;
    
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(String timestamp, Integer httpStatus, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.httpStatus = httpStatus;
		this.message = message;
		this.details = details;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ErrorResponse [timestamp=" + timestamp + ", httpStatus=" + httpStatus + ", message=" + message
				+ ", details=" + details + "]";
	}
}
