package rfi.monpaniervert.managers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.PasswordDTO;
import rfi.monpaniervert.dto.TdbUserDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.enums.EStatusUser;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.exceptions.NotFoundException;

public interface UserManager {

	User add(User user);
	
	User put(Long id, User user) throws NotFoundException;
	
	void delete(Long id);
	
	User getByEmail(String email);
	
	User getById(Long id) throws NotFoundException;
	
	Boolean existsByEmail(String email);

	User findByEmail(String email) throws NotFoundException;

	Page<UserDTO> find(TdbUserDTO tdbUserDTO, Pageable pagination);

	User updatePassword(Long id, PasswordDTO passwordDTO) throws NotFoundException;

	void putStatus(Long id, EStatusUser status);

	void putNbTentative(User user, Integer nb);

	void putLastConnexionDate(Long id);

	User generatedTokenResetPassword(@Valid String email);

	User updatePasswordByEmail(String email, String password, Long id, String token);

}
