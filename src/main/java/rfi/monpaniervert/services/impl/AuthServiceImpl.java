package rfi.monpaniervert.services.impl;

import java.text.MessageFormat;
import java.util.HashSet;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.dto.JwtResponse;
import rfi.monpaniervert.dto.LoginRequestDTO;
import rfi.monpaniervert.dto.ResetPasswordDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.enums.ERole;
import rfi.monpaniervert.enums.EStatusUser;
import rfi.monpaniervert.enums.ErrorException;
import rfi.monpaniervert.exceptions.LoginExistingException;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.exceptions.SigninException;
import rfi.monpaniervert.exceptions.TokenInvalidOrExpiredException;
import rfi.monpaniervert.managers.RoleManager;
import rfi.monpaniervert.managers.UserManager;
import rfi.monpaniervert.mappers.UserMapper;
import rfi.monpaniervert.models.Role;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.security.jwt.JwtUtils;
import rfi.monpaniervert.security.services.UserDetailsImpl;
import rfi.monpaniervert.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtUtils jwtUtils;
	@Autowired private RoleManager roleManager;
	@Autowired private PasswordEncoder encoder;
	@Autowired private UserManager userManager;
	
	@Override
	public UserDTO register(UserDTO userDto) throws NotFoundException, LoginExistingException {
		
		if (this.userManager.existsByEmail(userDto.getEmail())) {
			throw new LoginExistingException("L'adresse mail est déjà utilisé");
		}
	
		final User user = UserMapper.INSTANCE.toEntity(userDto);
		user.setStatus(EStatusUser.ACTIF);
		user.setNbTentative(3);
		final Role role = this.roleManager.getByName(ERole.CLIENT);
		user.setRoles(new HashSet<>());
		user.getRoles().add(role);
		user.setPassword(encoder.encode(userDto.getPassword()));
		return UserMapper.INSTANCE.toDto(this.userManager.add(user));	
	}

	@Override
	public JwtResponse authenticate(@Valid LoginRequestDTO loginRequest) {	
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			if(this.userManager.existsByEmail(loginRequest.getUsername())) {
				final User user = this.userManager.getByEmail(loginRequest.getUsername());
				final Integer nbTentative = user.getNbTentative();
				if(user.getNbTentative() == 0 && user.getStatus().equals(EStatusUser.BLOQUE)) {
					throw new SigninException(MessageFormat.format("Le compte utilisateur {0} est bloqué", loginRequest.getUsername()), ErrorException.LOCK_ACCOUNT);	
				}
				if(nbTentative > 0) {
					this.userManager.putNbTentative(user, (nbTentative - 1));
				}
			}
			throw new SigninException(MessageFormat.format("Les informations saisies ne correspondent pas: {0}.", loginRequest.getUsername()), ErrorException.ERROR_LOGIN);	
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
		switch (userDetails.getStatus()) {
			case DESACTIVE:
				throw new SigninException(MessageFormat.format("Le compte utilisateur {0} est désactivé", userDetails.getEmail()), ErrorException.DISABLED_ACCOUNT);	
			case BLOQUE:
				throw new SigninException(MessageFormat.format("Le compte utilisateur {0} est bloqué", userDetails.getEmail()), ErrorException.LOCK_ACCOUNT);	
			case ACTIF:
			default:
				break;
		}

		this.userManager.putLastConnexionDate(userDetails.getId());
		final JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setUser(UserMapper.INSTANCE.toDto(this.userManager.getByEmail(userDetails.getEmail())));
		jwtResponse.setToken(jwtUtils.generateJwtToken(authentication));
		return jwtResponse;
		

	}

	@Override
	public void checkEmail(@NotNull String email) {
		if(this.userManager.existsByEmail(email)) {
			throw new LoginExistingException("L'adresse mail est déjà utilisé");
		}
	}

	@Override
	public UserDTO getUserByTokenResetPassword(@NotNull String token) {
		if (this.jwtUtils.validateJwtToken(token)) {
			final String email = this.jwtUtils.getUserNameFromJwtToken(token);
			return UserMapper.INSTANCE.toDto(this.userManager.getByEmail(email));
		} 
		throw new TokenInvalidOrExpiredException("Le token est expiré ou invalide", ErrorException.TOKEN_RESET_PASSWORD_INVALID);	
	}

	@Override
	public void updateNewPasswordByToken(@Valid ResetPasswordDTO resetPasswordDTO) {
		final String token = resetPasswordDTO.getToken();
		if(jwtUtils.validateJwtToken(token)) {
			final String email = this.jwtUtils.getUserNameFromJwtToken(token);
			this.userManager.updatePasswordByEmail(email, resetPasswordDTO.getPassword(), resetPasswordDTO.getId(), resetPasswordDTO.getToken());
		} else {
			throw new TokenInvalidOrExpiredException("Le token est expiré ou invalide", ErrorException.TOKEN_RESET_PASSWORD_INVALID);	
		}
	}
}
