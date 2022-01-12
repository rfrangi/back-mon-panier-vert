package rfi.monpaniervert.exceptions;

import rfi.monpaniervert.enums.ErrorException;

public class PseudoExistingException extends ExistingException {


	private static final long serialVersionUID = 1L;
	
	public PseudoExistingException(String msg) {
		super(ErrorException.PSEUDO_EXISTING, msg);
	}
}

