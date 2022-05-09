package rfi.monpaniervert.controllers;

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

import rfi.monpaniervert.dto.ProduitByCatDTO;
import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.dto.TdbProduitDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.services.ProduitService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/produit")
public class ProduitController {

	@Autowired private ProduitService produitService;
		
	@RequestMapping(value = "/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<ProduitDTO> find(@RequestBody TdbProduitDTO tdbProduitDTO, Pageable pagination) {
		return this.produitService.find(tdbProduitDTO, pagination);
	}
	
	@RequestMapping(value = "sscat/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ProduitByCatDTO findByCategorie(@RequestBody TdbProduitDTO tdbProduitDTO, Pageable pagination) {
		return this.produitService.findByCategorie(tdbProduitDTO, pagination);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ProduitDTO getById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.produitService.getById(id);
	}
}
