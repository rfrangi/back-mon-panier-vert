package rfi.monpaniervert.exceptions;

public class UnAuthorizedException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -1684466079921901350L;

	public UnAuthorizedException() {
		super();
	}

	public UnAuthorizedException(String message) {
		super(message);
	}

}
