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

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.dto.TdbProduitDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.services.ProduitService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/produit")
public class AdminProduitController {

	@Autowired private ProduitService produitService;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<ProduitDTO> find(@RequestBody TdbProduitDTO tdbProduitDTO, Pageable pagination) {
		return this.produitService.find(tdbProduitDTO, pagination);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	ProduitDTO add(
			@RequestParam("produit") String produitDTO,  
			@RequestParam(value = "files", required= false) MultipartFile files) throws JsonProcessingException {
    	final ProduitDTO produitObj = this.mapper.readValue(produitDTO, ProduitDTO.class);
    	return this.produitService.add(produitObj, files);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	ProduitDTO put(
			@PathVariable(value = "id") Long id,
			@RequestParam("produit") String produitDTO,  
			@RequestParam(value = "files", required= false) MultipartFile files) throws JsonProcessingException {
    	final ProduitDTO produitObj = this.mapper.readValue(produitDTO, ProduitDTO.class);
    	produitObj.setId(id);
    	return this.produitService.put(produitObj, files);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id) {
		this.produitService.delete(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ProduitDTO getById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.produitService.getById(id);
	}
}
