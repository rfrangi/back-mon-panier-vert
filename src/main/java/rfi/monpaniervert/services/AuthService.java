package rfi.monpaniervert.services;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import rfi.monpaniervert.dto.JwtResponse;
import rfi.monpaniervert.dto.LoginRequestDTO;
import rfi.monpaniervert.dto.ResetPasswordDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.exceptions.LoginExistingException;
import rfi.monpaniervert.exceptions.NotFoundException;

public interface AuthService {

	UserDTO register(UserDTO userDto) throws NotFoundException, LoginExistingException;

	JwtResponse authenticate(@Valid LoginRequestDTO loginRequest);

	void checkEmail(@NotNull String email);

	UserDTO getUserByTokenResetPassword(@NotNull String token);

	void updateNewPasswordByToken(@Valid ResetPasswordDTO resetPasswordDTO);
}
