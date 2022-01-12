package rfi.monpaniervert.services;

import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.models.Adresse;

public interface AdresseService {

	
	Adresse getById(Long id) throws NotFoundException;
	
	Adresse put(Long id, Adresse user);

	

}
