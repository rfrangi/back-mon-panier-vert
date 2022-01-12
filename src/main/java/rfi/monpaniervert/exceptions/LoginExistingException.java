package rfi.monpaniervert.exceptions;

import rfi.monpaniervert.enums.ErrorException;

public class LoginExistingException extends ExistingException {

	private static final long serialVersionUID = 1L;
	
	public LoginExistingException(String msg) {
		super(ErrorException.EMAIL_EXISTING, msg);
	}
}
