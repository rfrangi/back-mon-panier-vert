package rfi.monpaniervert.controllers.admin;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.services.SiteService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/sites")
public class AdminSiteController {

	@Autowired private SiteService siteService;
	private final ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<SiteDTO> find(@RequestBody TdbSiteDTO tdbSiteDTO, Pageable pagination) {
		return this.siteService.find(tdbSiteDTO, pagination);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Site create(
			@RequestParam("site") String site,
			@RequestParam(value = "files", required= false ) MultipartFile files) throws JsonProcessingException {
		
    	final Site siteObj = this.mapper.readValue(site, Site.class);
		return this.siteService.create(siteObj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Site put(
			@PathVariable(value = "id") Long id, 
			@RequestParam("site") String site,  
			@RequestParam(value = "files", required= false ) MultipartFile files) throws JsonProcessingException {
    	final Site siteObj = this.mapper.readValue(site, Site.class);
		return this.siteService.put(siteObj.getId(), siteObj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id) {
		this.siteService.delete(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public SiteDTO getById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.siteService.getById(id);
	}
	
	@RequestMapping(value = "/{id}/compagnies", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<CompagnieDTO> findCompagnies(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.siteService.findCompagnies(id);
	}
	
	@RequestMapping(value = "/{id}/compagnie/{idCompagnie}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public SiteDTO addCompagnie(
			@PathVariable(value = "id") Long id,
			@PathVariable(value = "idCompagnie") Long idCompagnie,
			@Valid @RequestBody Site site) throws NotFoundException {
		return this.siteService.addCompagnie(id, idCompagnie);
	}
	
	@RequestMapping(value = "/{id}/compagnies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public SiteDTO addCompagnies(@PathVariable(value = "id") Long id, @RequestBody List<CompagnieDTO> compagnies) throws NotFoundException {
		return this.siteService.addCompagnies(id, compagnies);
	}
	
	@RequestMapping(value = "/{id}/compagnie/{idCompagnie}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void removeCompagnie(
			@PathVariable(value = "id") Long id,
			@PathVariable(value = "idCompagnie") Long idCompagnie) throws NotFoundException {
		this.siteService.removeCompagnie(id, idCompagnie);
	}
}
