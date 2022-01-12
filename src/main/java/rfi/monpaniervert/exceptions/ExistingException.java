package rfi.monpaniervert.exceptions;

import rfi.monpaniervert.enums.ErrorException;

public class ExistingException extends RuntimeException {


		private static final long serialVersionUID = 6055302690312521309L;

		/**
		 * the Error Code. This is documented in {@code TriumErrorCodes}.
		 */
		private final ErrorException errorCode;

		/**
		 * Constructor of the {@code TriumBusinessException}.
		 * 
		 * @param errorCode : error code from Trium WS.
		 * @param message : error message from Trium WS
		 */
		public ExistingException(ErrorException errorCode, String message) {
			super(message);
			this.errorCode = errorCode;
		}


		public ErrorException getErrorCode() {
			return errorCode;
		}
}