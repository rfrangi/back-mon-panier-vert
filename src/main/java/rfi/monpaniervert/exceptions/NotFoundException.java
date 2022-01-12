package rfi.monpaniervert.exceptions;

import rfi.monpaniervert.enums.ErrorException;

public class NotFoundException extends ExistingException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String msg) {
		super(ErrorException.NOT_FOUND, msg);
	}
}
