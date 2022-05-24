package rfi.monpaniervert.managers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.CommandeClientDTO;
import rfi.monpaniervert.dto.TdbCommandeDTO;
import rfi.monpaniervert.models.CommandeClient;

public interface CommandeClientManager {
	

	CommandeClient save(CommandeClient commandeClient);

	Page<CommandeClientDTO> find(TdbCommandeDTO tdbCommandeDTO, Pageable pagination);
	
	CommandeClient getById(Long id);

}
