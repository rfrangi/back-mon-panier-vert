package rfi.monpaniervert.managers.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.dto.TdbCompagnieDTO;
import rfi.monpaniervert.managers.CompagnieManager;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.repository.CompagnieRepository;
import rfi.monpaniervert.exceptions.NotFoundException;

@Component
public class CompagnieManagerImpl  implements CompagnieManager {

	@Autowired
	private CompagnieRepository compagnieRepository;
	
	
	@Override
	public Compagnie create(Compagnie compagnie) {
		return this.compagnieRepository.save(compagnie);
	}

	@Override
	public Compagnie getById(Long id) throws NotFoundException {
		final Compagnie compagnie = this.compagnieRepository.getById(id);
		if(compagnie == null) {
			throw new NotFoundException(MessageFormat.format("Compagnie with id: {0} couldn''t be found", id));
		}
		return compagnie;
	}

	@Override
	public Compagnie put(Long id, Compagnie compagnie) {
		compagnie.setId(id);
		return this.compagnieRepository.save(compagnie);
	}

	@Override
	public void delete(Long id) {
		this.compagnieRepository.deleteById(id);
	}

	@Override
	public Page<Compagnie> find(TdbCompagnieDTO tdbCompagnieDTO, Pageable pagination) {
		final String searchTerm = tdbCompagnieDTO.getSearchTerm() == "" ? null : tdbCompagnieDTO.getSearchTerm().equals(null) ? null : tdbCompagnieDTO.getSearchTerm().trim();
		return this.compagnieRepository.find(searchTerm, pagination);
	}

	
}
