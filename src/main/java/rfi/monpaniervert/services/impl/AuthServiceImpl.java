package rfi.monpaniervert.services.impl;

import java.util.HashSet;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.dto.JwtResponse;
import rfi.monpaniervert.dto.LoginRequestDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.enums.ERole;
import rfi.monpaniervert.exceptions.LoginExistingException;
import rfi.monpaniervert.managers.RoleManager;
import rfi.monpaniervert.managers.UserManager;
import rfi.monpaniervert.mappers.UserMapper;
import rfi.monpaniervert.models.Role;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.security.jwt.JwtUtils;
import rfi.monpaniervert.security.services.UserDetailsImpl;
import rfi.monpaniervert.services.AuthService;
import rfi.monpaniervert.exceptions.NotFoundException;

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
		final Role role = this.roleManager.getByName(ERole.CLIENT);
		user.setRoles(new HashSet<>());
		user.getRoles().add(role);
		user.setPassword(encoder.encode(userDto.getPassword()));
		return UserMapper.INSTANCE.toDto(this.userManager.add(user));	
	}

	@Override
	public JwtResponse authenticate(@Valid LoginRequestDTO loginRequest) {		
		final Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
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
}
