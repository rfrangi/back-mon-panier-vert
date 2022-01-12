package rfi.monpaniervert.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.managers.AdresseManager;
import rfi.monpaniervert.models.Adresse;
import rfi.monpaniervert.services.AdresseService;
import rfi.monpaniervert.exceptions.NotFoundException;

@Service
public class AdresseServiceImpl implements AdresseService {

	@Autowired private AdresseManager adresseManager;

	@Override
	public Adresse getById(Long id) throws NotFoundException {
		return this.adresseManager.getById(id);
	}

	@Override
	public Adresse put(Long id, Adresse adresse) {
		adresse.setIdAdresse(id);
		return this.adresseManager.put(id, adresse);
	}
}
