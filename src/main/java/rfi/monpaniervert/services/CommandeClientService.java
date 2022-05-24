package rfi.monpaniervert.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.CommandeClientDTO;
import rfi.monpaniervert.dto.TdbCommandeDTO;
import rfi.monpaniervert.models.CommandeClient;

public interface CommandeClientService {

	CommandeClient post(CommandeClientDTO commandeClientDTO);

	Page<CommandeClientDTO> findByUser(TdbCommandeDTO tdbCommandeDTO, Pageable pagination);
	
	CommandeClient getById(Long id);

	Page<CommandeClientDTO> findByCompagnie(TdbCommandeDTO tdbCommandeDTO, Pageable pagination);


	Page<CommandeClientDTO> findBySite(TdbCommandeDTO tdbCommandeDTO, Pageable pagination);


}
