package rfi.monpaniervert.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.exceptions.NotFoundException;

public interface SiteService {

	
	Site getById(Long id) throws NotFoundException;
	
	Site put(Long id, Site site);
	
	void delete(Long id);
	
	Site create(Site site);

	Page<Site> find(TdbSiteDTO tdbSiteDTO, Pageable pagination);
}
