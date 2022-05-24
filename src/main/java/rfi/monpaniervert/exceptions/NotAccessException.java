package rfi.monpaniervert.exceptions;

import rfi.monpaniervert.enums.ErrorException;

public class NotAccessException extends ExistingException {

	private static final long serialVersionUID = 1L;
	
	public NotAccessException(String msg) {
		super(ErrorException.RESSOURCE_NOT_ACCESS, msg);
	}
}
