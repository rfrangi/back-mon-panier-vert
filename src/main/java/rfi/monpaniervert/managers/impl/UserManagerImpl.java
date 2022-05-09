package rfi.monpaniervert.managers.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.dto.PasswordDTO;
import rfi.monpaniervert.dto.TdbUserDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.enums.ERole;
import rfi.monpaniervert.enums.EStatusUser;
import rfi.monpaniervert.enums.ErrorException;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.exceptions.PasswordException;
import rfi.monpaniervert.exceptions.TokenInvalidOrExpiredException;
import rfi.monpaniervert.managers.UserManager;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.repository.UserRepository;
import rfi.monpaniervert.security.jwt.JwtUtils;
import rfi.monpaniervert.security.services.UserDetailsImpl;

@Component
public class UserManagerImpl  implements UserManager {

	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder encoder;
	@Autowired private JwtUtils jwtUtils;

	@Override
	public User add(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User getById(Long id) throws NotFoundException {
		final User user = this.userRepository.getById(id);
		if(user == null) {
			throw new NotFoundException(MessageFormat.format("User with id: {0} couldn''t be found", id));
		}
		return user;
	}

	@Override
	public User put(Long id, User user) throws NotFoundException {
		final User userBDD = this.getById(id);
		userBDD.setModificationDate(new Date());
		userBDD.setFirstname(user.getFirstname());
		userBDD.setLastname(user.getLastname());
		userBDD.setCivilite(user.getCivilite());
		userBDD.setEmail(user.getEmail());
		userBDD.setRecevoirOffre(user.getRecevoirOffre());
		return this.userRepository.save(userBDD);
	}
	
	@Override
	public void putStatus(Long id, EStatusUser status) throws NotFoundException {
		final User userBDD = this.getById(id);
		userBDD.setChangeStatusDate(new Date());
		userBDD.setStatus(status);
		this.userRepository.save(userBDD);
	}

	@Override
	public void delete(Long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public User getByEmail(String email) {
		return this.userRepository.getByEmail(email);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return this.userRepository.existsByEmail(email);
	}
	
	@Override
	public void putNbTentative(User user, Integer nb) {
		user.setNbTentative(nb);
		if(nb == 0) {
			user.setStatus(EStatusUser.BLOQUE);
		}
		this.userRepository.save(user);
	}


	@Override
	public User findByEmail(String email) throws NotFoundException {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("User with email: {0} couldn''t be found", email)));
	}

	@Override
	public Page<UserDTO> find(TdbUserDTO tdbUserDTO, Pageable pagination) {
		final String searchTerm = tdbUserDTO.getSearchTerm() == "" ? null : tdbUserDTO.getSearchTerm().equals(null) ? null : tdbUserDTO.getSearchTerm().trim();
		return this.userRepository.find(searchTerm, pagination);
	}

	@Override
	public User updatePassword(Long id, PasswordDTO passwordDTO) throws NotFoundException {
		final User user = this.getById(id);
		final UserDetailsImpl securityUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		/** SI L'ANCIEN MDP EST DIFFERENT OU SI LE PROFIL NEST PAS ADMIN **/
		if(!securityUser.getAuthorities().stream().map(x -> x.toString()).collect(Collectors.toList()).contains(ERole.ADMIN.toString())
				|| !this.encoder.matches(passwordDTO.getPassword(), user.getPassword())) {
			throw new PasswordException("", ErrorException.MDP_INVALID);
		}
		
		user.setPassword(encoder.encode(passwordDTO.getNewPassword()));
		return this.add(user);
	}
	
	@Override
	public User updatePasswordByEmail(String email, String password, Long id, String token) {
		final User user = this.getByEmail(email);
		if(!user.getId().equals(id) && !token.equals(user.getTokenResetPassword())) {
			throw new TokenInvalidOrExpiredException("Le token est expiré ou invalide", ErrorException.TOKEN_RESET_PASSWORD_INVALID);	
		}
		user.setPassword(encoder.encode(password));
		user.setTokenResetPassword(null);
		return this.userRepository.save(user);
	}

	@Override
	public void putLastConnexionDate(Long id) {
		final User user = this.getById(id);
		user.setLastConnexionDate(new Date());
		user.setNbTentative(3);
		this.userRepository.save(user);
		
	}

	@Override
	public User generatedTokenResetPassword(String email) {
		User user = null;
		if(this.userRepository.existsByEmail(email)) {
			user = this.getByEmail(email);
			final String token = this.jwtUtils.generateTokenForResetPassword(email);
			user.setTokenResetPassword(token);
			user = this.userRepository.save(user);
		}
		
		return user;
	}
}
