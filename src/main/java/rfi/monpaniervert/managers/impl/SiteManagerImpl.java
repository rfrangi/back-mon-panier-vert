package rfi.monpaniervert.managers.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.enums.EStatusSite;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.managers.SiteManager;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.repository.SiteRepository;

@Component
public class SiteManagerImpl  implements SiteManager {

	@Autowired
	private SiteRepository siteRepository;
	
	
	@Override
	public Site create(Site site) {
		site.setCreationDate(new Date());
		return this.siteRepository.save(site);
	}

	@Override
	public Site getById(Long id) throws NotFoundException {
		final Site site = this.siteRepository.findById(id).get();
		if(site == null) {
			throw new NotFoundException(MessageFormat.format("Site with id: {0} couldn''t be found", id));
		}
		return site;
	}

	@Override
	public Site put(Long id, Site site) {
		site.setId(id);
		site.setModificationDate(new Date());
		return this.siteRepository.save(site);
	}

	@Override
	public void delete(Long id) {
		this.siteRepository.deleteById(id);
	}

	@Override
	public Page<SiteDTO> find(TdbSiteDTO tdbSiteDTO, Pageable pagination) {
		String searchTerm = null;
		if(tdbSiteDTO.getSearchTerm()  != null) {
			searchTerm = tdbSiteDTO.getSearchTerm() == "" ? null : tdbSiteDTO.getSearchTerm().equals(null) ? null : tdbSiteDTO.getSearchTerm().trim();
		}
		
		String searchByAdresse = null;
		if(tdbSiteDTO.getSearchByAdresse()  != null) {
			searchByAdresse = tdbSiteDTO.getSearchByAdresse() == "" ? null : tdbSiteDTO.getSearchByAdresse().equals(null) ? null : tdbSiteDTO.getSearchByAdresse().trim();
		}
		final List<EStatusSite> status = tdbSiteDTO.getStatus() != null ? tdbSiteDTO.getStatus() : null;

		return this.siteRepository.find(searchTerm, searchByAdresse, status, pagination);
	}

	@Override
	public List<Compagnie> findCompagnies(Long id) {
		return this.siteRepository.findCompagnies(id);

	}

	@Override
	public Site addCompagnie(Long id, Compagnie compagnie) {
		final Site site = this.siteRepository.findById(id).get();
		final Set<Compagnie> compagnies = site.getCompagnies();
		compagnies.add(compagnie);
		site.setCompagnies(compagnies);
		return this.siteRepository.save(site);
	}

	@Override
	public Site addCompagnies(Long id, @Valid List<Compagnie> compagnies) {
		final Site site = this.siteRepository.findById(id).get();
		final Set<Compagnie> compagniesSite = site.getCompagnies();
		compagniesSite.addAll(compagnies);
		site.setCompagnies(compagniesSite);
		return this.siteRepository.save(site);
	}

	@Override
	public void removeCompagnie(Long id, Long idCompagnie) {
		final Site site = this.siteRepository.findById(id).get();
		final Set<Compagnie> compagniesSite = site.getCompagnies();
		final Set<Compagnie> newCompagnies = compagniesSite.stream().filter(compagnie -> !compagnie.getId().equals(idCompagnie)).collect(Collectors.toSet());
		site.setCompagnies(newCompagnies);
		this.siteRepository.save(site);		
	}
}
