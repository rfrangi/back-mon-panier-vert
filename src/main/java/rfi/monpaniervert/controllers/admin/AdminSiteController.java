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

import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.services.SiteService;
import rfi.monpaniervert.exceptions.NotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/sites")
public class AdminSiteController {

	@Autowired private SiteService siteService;

	@RequestMapping(value = "/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<Site> find(@RequestBody TdbSiteDTO tdbSiteDTO, Pageable pagination) {
		return this.siteService.find(tdbSiteDTO, pagination);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Site create(@Valid @RequestBody Site site) {
		return this.siteService.create(site);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Site put(@PathVariable(value = "id") Long id, @Valid @RequestBody Site site) {
		return this.siteService.put(id, site);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id) {
		this.siteService.delete(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Site getById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.siteService.getById(id);
	}
}
