package rfi.monpaniervert.controllers.admin;

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

import rfi.monpaniervert.dto.TdbMailDTO;
import rfi.monpaniervert.models.Email;
import rfi.monpaniervert.services.MailService;
import rfi.monpaniervert.exceptions.NotFoundException;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/emails")
public class AdminMailController  {

	@Autowired private MailService mailService;
	
	@RequestMapping(value="mailSimpleText", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void testSimpleEmail() throws NotFoundException {
		this.mailService.sendSimpleMessage("romain.frangi@cleverconnect.com", "Test simple Message", "Hello, test simple message");
	}
	
	@RequestMapping(value="resetMDP", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void testResetMDP() throws NotFoundException {
		this.mailService.resetPassword("frangi.romain@gmail.com");
	}
	
	@RequestMapping(value = "/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<Email> find(@RequestBody TdbMailDTO tdbMailDTO, Pageable pagination) {
		return this.mailService.find(tdbMailDTO, pagination);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id) {
		this.mailService.delete(id);
	}
}