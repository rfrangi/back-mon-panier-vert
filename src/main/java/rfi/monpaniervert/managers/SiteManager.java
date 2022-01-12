package rfi.monpaniervert.managers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.exceptions.NotFoundException;

public interface SiteManager {

	Site create(Site user);
	
	Site put(Long id, Site user);
	
	void delete(Long id);
	
	Site getById(Long id) throws NotFoundException;

	Page<Site> find(TdbSiteDTO tdbSiteDTO, Pageable pagination);
}
