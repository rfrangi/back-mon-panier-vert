package rfi.monpaniervert.security;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthentException extends AuthenticationException {

	/**
	 *
	 */
	private static final long serialVersionUID = -4370117542434671751L;
	AuthentificationError authError;

	public CustomAuthentException(String msg) {
		super(msg);
	}

	public CustomAuthentException(String msg, AuthentificationError authError) {
		super(msg);
		this.authError = authError;
	}

	public AuthentificationError getAuthError() {
		return this.authError;
	}

	public void setAuthError(AuthentificationError authError) {
		this.authError = authError;
	}
}
