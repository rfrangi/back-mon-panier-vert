package rfi.monpaniervert.exceptions;

import java.util.Date;

import rfi.monpaniervert.enums.ErrorException;

public class ErrorMessage {
	private Date timestamp;
    private String message;
    private ErrorException code;
    public ErrorMessage() {}
	public ErrorMessage(Date timestamp, String message, ErrorException code) {
		this.timestamp = timestamp;
		this.message = message;
		this.code = code;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorException getCode() {
		return code;
	}
	public void setCode(ErrorException code) {
		this.code = code;
	}

}
