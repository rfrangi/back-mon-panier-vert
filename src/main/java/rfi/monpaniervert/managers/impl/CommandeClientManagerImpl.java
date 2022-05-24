package rfi.monpaniervert.managers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.dto.CommandeClientDTO;
import rfi.monpaniervert.dto.TdbCommandeDTO;
import rfi.monpaniervert.enums.EStatusCommande;
import rfi.monpaniervert.managers.CommandeClientManager;
import rfi.monpaniervert.models.CommandeClient;
import rfi.monpaniervert.repository.CommandeClientRepository;

@Component
public class CommandeClientManagerImpl  implements CommandeClientManager {

	@Autowired private CommandeClientRepository commandeClientRepository;

	@Override
	public CommandeClient save(CommandeClient commandeClient) {
		return this.commandeClientRepository.save(commandeClient);
	}

	@Override
	public Page<CommandeClientDTO> find(TdbCommandeDTO tdbCommandeDTO, Pageable pagination) {
		final List<EStatusCommande> status = tdbCommandeDTO.getStatus() != null ? tdbCommandeDTO.getStatus() : null;
		return this.commandeClientRepository.find(tdbCommandeDTO.getUserId(), tdbCommandeDTO.getSiteId(),status, tdbCommandeDTO.getCompagnieId(), pagination);
	}

	@Override
	public CommandeClient getById(Long id) {
		return this.commandeClientRepository.findById(id).get();
	}
}
