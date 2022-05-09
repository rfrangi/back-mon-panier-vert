package rfi.monpaniervert.managers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.Site;

public interface SiteManager {

	Site create(Site user);
	
	Site put(Long id, Site user);
	
	void delete(Long id);
	
	Site getById(Long id) throws NotFoundException;

	Page<SiteDTO> find(TdbSiteDTO tdbSiteDTO, Pageable pagination);

	List<Compagnie> findCompagnies(Long id);

	Site addCompagnie(Long id, Compagnie Compagnie);

	Site addCompagnies(Long id, @Valid List<Compagnie> compagnies);

	void removeCompagnie(Long id, Long idCompagnie);
}
