package rfi.monpaniervert.exceptions;

import rfi.monpaniervert.enums.ErrorException;

public class TokenInvalidOrExpiredException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1709391173595266455L;
	ErrorException error;

	public TokenInvalidOrExpiredException() {
		super();
	}

	public TokenInvalidOrExpiredException(String message, ErrorException error) {
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
