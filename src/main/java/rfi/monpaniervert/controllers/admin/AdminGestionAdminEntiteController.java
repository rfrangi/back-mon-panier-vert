package rfi.monpaniervert.controllers.admin;

import java.util.List;

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

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.enums.ETypeEntite;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.models.AdminEntite;
import rfi.monpaniervert.services.AdminEntiteService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/administrateur/entite")
public class AdminGestionAdminEntiteController  {

	@Autowired private AdminEntiteService adminEntiteService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public AdminEntite post(@Valid @RequestBody AdminEntite adminEntite) {
		return this.adminEntiteService.post(adminEntite);
	}
	
	@RequestMapping(value = "user/{id}/{idEntite}/{type}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id, @PathVariable(value = "idEntite") Long idEntite, @PathVariable(value = "type") ETypeEntite type) {
		this.adminEntiteService.delete(id, idEntite, type);
	}
	
	@RequestMapping(value = "user/{id}/type/{type}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<UserDTO> getAdminForEntite(@PathVariable(value = "id") Long id, @PathVariable(value = "type") ETypeEntite type) throws NotFoundException {
		return this.adminEntiteService.getAdminForEntite(id, type);
	}
	
	@RequestMapping(value = "compagnie/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<CompagnieDTO> getCompagnieByUser(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.adminEntiteService.getCompagnieByUser(id);
	}
	
	@RequestMapping(value = "site/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<SiteDTO> getSiteByUser(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.adminEntiteService.getSiteByUser(id);
	}
	
	

}