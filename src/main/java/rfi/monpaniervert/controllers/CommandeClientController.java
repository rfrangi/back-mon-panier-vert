package rfi.monpaniervert.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
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
import rfi.monpaniervert.security.services.UserDetailsImpl;
import rfi.monpaniervert.services.UserService;
import rfi.monpaniervert.exceptions.NotFoundException;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/commandesClient")
public class CommandeClientController  {

	@Autowired private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO create(@Valid @RequestBody User user) {
		return this.userService.create(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO put(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO user) throws NotFoundException {
		final UserDetailsImpl securityUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return this.userService.put(securityUser.getId(), user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id) {
		final UserDetailsImpl securityUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		this.userService.delete(securityUser.getId());
	}
	
	@RequestMapping(value = "/{id}/password", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO updatePassword(@PathVariable(value = "id") Long id, @Valid @RequestBody PasswordDTO passwordDTO) throws NotFoundException {
		final UserDetailsImpl securityUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return this.userService.updatePassword(securityUser.getId(),passwordDTO);
	}
	
	@RequestMapping(value = "/{id}/email/{email}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDTO putEmail(@PathVariable(value = "id") Long id, @PathVariable(value = "email") String email, @Valid @RequestBody User user) throws NotFoundException {
		final UserDetailsImpl securityUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return this.userService.putEmail(securityUser.getId(), email);
	}
	
}