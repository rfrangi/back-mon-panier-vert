package rfi.monpaniervert.controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rfi.monpaniervert.dto.PasswordDTO;
import rfi.monpaniervert.dto.TdbUserDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.services.UserService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/users")
public class AdminUserController  {

	@Autowired private UserService userService;
	
	@RequestMapping(value = "/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<UserDTO> find(@RequestBody TdbUserDTO tdbUserDTO, Pageable pagination) {
		return this.userService.find(tdbUserDTO, pagination);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO create(@Valid @RequestBody User user) {
		return this.userService.create(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO put(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO UserDTO) throws NotFoundException {
		return this.userService.put(id, UserDTO);
	}
	
	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void putStatus(@PathVariable(value = "id") Long id, @Valid @RequestBody String status) {
		this.userService.updateStatus(id, status);
	}
	
	@RequestMapping(value = "/{id}/email/{email}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO putEmail(@PathVariable(value = "id") Long id, @PathVariable(value = "email") String email, @Valid @RequestBody UserDTO user) throws NotFoundException {
		return this.userService.putEmail(id, email);
	}
	
	@RequestMapping(value = "/{id}/password", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO updatePassword(@PathVariable(value = "id") Long id, @Valid @RequestBody PasswordDTO passwordDTO) throws NotFoundException {
		return this.userService.updatePassword(id,passwordDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id) {
		this.userService.delete(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO getById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.userService.getById(id);
	}
}