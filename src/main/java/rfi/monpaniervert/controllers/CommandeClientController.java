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

import rfi.monpaniervert.dto.CommandeClientDTO;
import rfi.monpaniervert.dto.TdbCommandeDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.models.CommandeClient;
import rfi.monpaniervert.services.CommandeClientService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/commandesClient")
public class CommandeClientController  {

	@Autowired private CommandeClientService commandeClientService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CommandeClient create(@RequestBody CommandeClientDTO commandeClient) {
		return this.commandeClientService.post(commandeClient);
	}
	
	@RequestMapping(value = "/paginated", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<CommandeClientDTO> find(@RequestBody TdbCommandeDTO tdbCommandeDTO, Pageable pagination) {
		return this.commandeClientService.findByUser(tdbCommandeDTO, pagination);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CommandeClient getById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return this.commandeClientService.getById(id);
	}
}