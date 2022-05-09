package rfi.monpaniervert.exceptions;

import rfi.monpaniervert.enums.ErrorException;

public class SigninException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1709391173595266455L;
	ErrorException error;

	public SigninException() {
		super();
	}

	public SigninException(String message, ErrorException error) {
		super(message);
		this.error = error;

	}

	public ErrorException getError() {
		return this.error;
	}

	public void setError(ErrorException error) {
		this.error = error;
	}
}
