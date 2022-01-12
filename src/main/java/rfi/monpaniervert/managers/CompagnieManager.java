package rfi.monpaniervert.managers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.TdbCompagnieDTO;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.exceptions.NotFoundException;

public interface CompagnieManager {

	Compagnie create(Compagnie user);
	
	Compagnie put(Long id, Compagnie user);
	
	void delete(Long id);
	
	Compagnie getById(Long id) throws NotFoundException;

	Page<Compagnie> find(TdbCompagnieDTO tdbCompagnieDTO, Pageable pagination);
}