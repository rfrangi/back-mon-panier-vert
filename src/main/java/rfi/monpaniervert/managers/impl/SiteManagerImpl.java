package rfi.monpaniervert.managers.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.managers.SiteManager;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.repository.SiteRepository;

@Component
public class SiteManagerImpl  implements SiteManager {

	@Autowired
	private SiteRepository siteRepository;
	
	
	@Override
	public Site create(Site site) {
		return this.siteRepository.save(site);
	}

	@Override
	public Site getById(Long id) throws NotFoundException {
		final Site site = this.siteRepository.getById(id);
		if(site == null) {
			throw new NotFoundException(MessageFormat.format("Site with id: {0} couldn''t be found", id));
		}
		return site;
	}

	@Override
	public Site put(Long id, Site site) {
		site.setId(id);
		return this.siteRepository.save(site);
	}

	@Override
	public void delete(Long id) {
		this.siteRepository.deleteById(id);
	}

	@Override
	public Page<Site> find(TdbSiteDTO tdbSiteDTO, Pageable pagination) {
		final String searchTerm = tdbSiteDTO.getSearchTerm() == "" ? null : tdbSiteDTO.getSearchTerm().equals(null) ? null : tdbSiteDTO.getSearchTerm().trim();
		return this.siteRepository.find(searchTerm, pagination);
	}

	
}
