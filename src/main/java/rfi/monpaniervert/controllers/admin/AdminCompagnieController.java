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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.TdbCompagnieDTO;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.services.CompagnieService;
import rfi.monpaniervert.exceptions.NotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/compagnies")
public class AdminCompagnieController {

	@Autowired private CompagnieService compagnieService;
	private final ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<Compagnie> find(@RequestBody TdbCompagnieDTO tdbCompagnieDTO, Pageable pagination) {
		return this.compagnieService.find(tdbCompagnieDTO, pagination);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	CompagnieDTO create(@RequestParam("compagnie") String compagnie,  @RequestParam(value = "files", required= false ) MultipartFile files) throws JsonProcessingException {
    	final Compagnie compagnieObj = this.mapper.readValue(compagnie, Compagnie.class);
    	return this.compagnieService.create(compagnieObj, files);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CompagnieDTO put(@PathVariable(value = "id") Long id, @RequestParam("compagnie") String compagnie,  @RequestParam(value = "files", required= false ) MultipartFile files) throws JsonProcessingException {
    	final Compagnie compagnieObj = this.mapper.readValue(compagnie, Compagnie.class);
    	return this.compagnieService.put(id, compagnieObj, files);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id) {
		this.compagnieService.delete(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CompagnieDTO getById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.compagnieService.getById(id);
	}
}
