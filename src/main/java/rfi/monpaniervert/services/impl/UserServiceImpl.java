package rfi.monpaniervert.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.dto.PasswordDTO;
import rfi.monpaniervert.dto.TdbUserDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.exceptions.LoginExistingException;
import rfi.monpaniervert.managers.UserManager;
import rfi.monpaniervert.mappers.UserMapper;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.services.UserService;
import rfi.monpaniervert.exceptions.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired private UserManager userManager;

	@Override
	public UserDTO getById(Long id) throws NotFoundException {
		return UserMapper.INSTANCE.toDto(this.userManager.getById(id));
	}

	@Override
	public UserDTO put(Long id, UserDTO userDTO) throws NotFoundException {
		userDTO.setId(id);
		return UserMapper.INSTANCE.toDto(this.userManager.put(id, UserMapper.INSTANCE.toEntity(userDTO)));
	}

	@Override
	public UserDTO putEmail(Long id, String email, User user) throws NotFoundException {
		
		if( this.userManager.existsByEmail(email)) {
			throw new LoginExistingException("Cette adresse mail est déjà utilisé");
		}
		user.setEmail(email);
		user.setId(id);
		return UserMapper.INSTANCE.toDto(this.userManager.put(id, user));
	}
	
	@Override
	public void delete(Long id) {
		this.userManager.delete(id);
		
	}

	@Override
	public User getByEmail(String email) {
		return this.userManager.getByEmail(email);
	}

	@Override
	public Page<UserDTO> find(TdbUserDTO tdbUserDTO, Pageable pagination) {
		return this.userManager.find(tdbUserDTO, pagination);
	}

	@Override
	public UserDTO create(User user) {
		return UserMapper.INSTANCE.toDto(this.userManager.add(user));
	}

	@Override
	public UserDTO updatePassword(Long id, PasswordDTO passwordDTO) throws NotFoundException {
		return UserMapper.INSTANCE.toDto(this.userManager.updatePassword(id, passwordDTO));
	}
}
