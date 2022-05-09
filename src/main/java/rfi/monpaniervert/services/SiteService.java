package rfi.monpaniervert.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.models.Site;

public interface SiteService {

	
	SiteDTO getById(Long id) throws NotFoundException;
	
	Site put(Long id, Site site);
	
	void delete(Long id);
	
	Site create(Site site);

	Page<SiteDTO> find(TdbSiteDTO tdbSiteDTO, Pageable pagination);

	List<CompagnieDTO> findCompagnies(Long id);

	SiteDTO addCompagnie(Long id, Long idCompagnie);

	SiteDTO addCompagnies(Long id, List<CompagnieDTO> compagniesDTO);

	void removeCompagnie(Long id, Long idCompagnie);

}
