package rfi.monpaniervert.exceptions;


import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class AppExceptionHandler {

/*	@ExceptionHandler(value = {BadCredentialsException.class})
	public ResponseEntity<Object> badCredentialsException(BadCredentialsException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ErrorException.BAD_CREDENTIALS.toString());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(value = {CodeVIPException.class})
	public ResponseEntity<Object> CodeVIPException(CodeVIPException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ex.getErrorCode());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.CONFLICT); 
	}*/
	
	@ExceptionHandler(value = { PasswordException.class })
	public ResponseEntity<Object> passwordException(PasswordException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ex.getError());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(value = { TokenInvalidOrExpiredException.class })
	public ResponseEntity<Object> tokenInvalidException(TokenInvalidOrExpiredException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ex.getError());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(value = { QuantiteNonDispoException.class })
	public ResponseEntity<Object> QuantiteException(QuantiteNonDispoException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ex.getError());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.CONFLICT); 
	}
	
	@ExceptionHandler(value = { SigninException.class })
	public ResponseEntity<Object> signinException(SigninException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ex.getError());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.LOCKED); 
	}
	
	@ExceptionHandler(value = {ExistingException.class})
	public ResponseEntity<Object> existingException(ExistingException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ex.getErrorCode());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.CONFLICT); 
	}
	
	
/*	@ExceptionHandler(value = {DisabledAccountException.class})
	public ResponseEntity<Object> DisabledAccountException(DisabledAccountException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ex.getErrorCode());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.LOCKED); 
	}
	
	@ExceptionHandler(value = {UserFBAuthenticateException.class})
	public ResponseEntity<Object> DisabledAccountException(UserFBAuthenticateException ex, WebRequest request){
		ErrorMessage err = new ErrorMessage(new Date(), ex.getMessage(), ex.getErrorCode());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
	}*/
}
