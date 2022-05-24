package rfi.monpaniervert.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.managers.CommandeCompagnieManager;
import rfi.monpaniervert.models.CommandeCompagnie;
import rfi.monpaniervert.repository.CommandeCompagnieRepository;

@Component
public class CommandeCompagnieManagerImpl  implements CommandeCompagnieManager {

	@Autowired private CommandeCompagnieRepository commandeCompagnieRepository;

	@Override
	public CommandeCompagnie save(CommandeCompagnie commandeCompagnie) {
		return this.commandeCompagnieRepository.save(commandeCompagnie);
	}
}
