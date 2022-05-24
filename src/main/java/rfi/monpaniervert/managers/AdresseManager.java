package rfi.monpaniervert.managers;

import rfi.monpaniervert.models.Adresse;
import rfi.monpaniervert.exceptions.NotFoundException;

public interface AdresseManager {
	
	Adresse put(Long id, Adresse adresse);
	
	Adresse getById(Long id) throws NotFoundException;

	Adresse save(Adresse adresse);
	
}
