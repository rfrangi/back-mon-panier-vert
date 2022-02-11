package rfi.monpaniervert.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.PasswordDTO;
import rfi.monpaniervert.dto.TdbUserDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.exceptions.NotFoundException;

public interface UserService {

	
	UserDTO getById(Long id) throws NotFoundException;
	
	UserDTO put(Long id, UserDTO user) throws NotFoundException;
	
	UserDTO putEmail(Long id,String email) throws NotFoundException;
	
	UserDTO updatePassword(Long id, PasswordDTO passwordDTO) throws NotFoundException;

	void delete(Long id);
	
	UserDTO create(User user);
	
	User getByEmail(String email);

	Page<UserDTO> find(TdbUserDTO tdbUserDTO, Pageable pagination);
}
