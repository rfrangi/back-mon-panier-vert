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

import rfi.monpaniervert.models.Adresse;
import rfi.monpaniervert.services.AdresseService;
import rfi.monpaniervert.exceptions.NotFoundException;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/adresses")
public class AdresseController  {

	@Autowired private AdresseService adresseService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Adresse put(@PathVariable(value = "id") Long id, @Valid @RequestBody Adresse adresse) {
		return this.adresseService.put(id, adresse);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Adresse getById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.adresseService.getById(id);
	}
}