package rfi.monpaniervert.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rfi.monpaniervert.dto.JwtResponse;
import rfi.monpaniervert.dto.LoginRequestDTO;
import rfi.monpaniervert.dto.ResetPasswordDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.exceptions.LoginExistingException;
import rfi.monpaniervert.services.AuthService;
import rfi.monpaniervert.services.MailService;
import rfi.monpaniervert.exceptions.NotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired private AuthService authService;
	@Autowired private MailService mailService;

	@PostMapping("/signin")
	public JwtResponse authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
		return this.authService.authenticate(loginRequest);
	}
	
	@GetMapping("/{email}/check")
	public void existsByEmail(@NotNull @PathVariable("email") String email) {
		 this.authService.checkEmail(email);
	}

	@PostMapping("/signup")
	public UserDTO registerUser(@Valid @RequestBody UserDTO userDto) throws LoginExistingException, NotFoundException {
		return this.authService.register(userDto);
	}
	
	@PostMapping("/reset")
	public void resetPassword(@Valid @RequestBody String email) {
		this.mailService.resetPassword(email);
	}
	
	@PostMapping("/token")
	public UserDTO getUserByTokenResetPassword(@Valid @RequestBody String token) {
		return this.authService.getUserByTokenResetPassword(token);
	}
	
	@PostMapping("/change-password")
	public void updateNewPasswordByToken(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
		this.authService.updateNewPasswordByToken(resetPasswordDTO);
	}
}
