package rfi.monpaniervert.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.services.UserService;
import rfi.monpaniervert.exceptions.NotFoundException;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController  {

	@Autowired private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO create(@Valid @RequestBody User user) {
		return this.userService.create(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO put(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO user) throws NotFoundException {
		return this.userService.put(id, user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id) {
		this.userService.delete(id);
	}
	
	@RequestMapping(value = "/{id}/password", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO updatePassword(@PathVariable(value = "id") Long id, @Valid @RequestBody PasswordDTO passwordDTO) throws NotFoundException {
		return this.userService.updatePassword(id,passwordDTO);
	}
}