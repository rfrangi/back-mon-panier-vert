package rfi.monpaniervert.managers.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.managers.AdresseManager;
import rfi.monpaniervert.models.Adresse;
import rfi.monpaniervert.repository.AdresseRepository;
import rfi.monpaniervert.exceptions.NotFoundException;

@Component
public class AdresseManagerImpl  implements AdresseManager {

	@Autowired private AdresseRepository adresseRepository;

	
	@Override
	public Adresse getById(Long id) throws NotFoundException {
        return this.adresseRepository.getByIdAdresse(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Adresse with id: {0} couldn''t be found", id)));
	}

	@Override
	public Adresse put(Long id, Adresse adresse) {
		return this.adresseRepository.save(adresse);
	}

	@Override
	public Adresse save(Adresse adresse) {
		return this.save(adresse);
	}
}
