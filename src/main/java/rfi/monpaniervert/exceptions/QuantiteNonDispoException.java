package rfi.monpaniervert.exceptions;

import rfi.monpaniervert.enums.ErrorException;

public class QuantiteNonDispoException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1709391173595266455L;
	ErrorException error;

	public QuantiteNonDispoException() {
		super();
	}

	public QuantiteNonDispoException(String message) {
		super(message);
		this.error = ErrorException.QUANTITE_NON_DISPO;

	}

	public ErrorException getError() {
		return this.error;
	}

	public void setError(ErrorException error) {
		this.error = error;
	}
}
