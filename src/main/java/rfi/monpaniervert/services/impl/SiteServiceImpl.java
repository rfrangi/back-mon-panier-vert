package rfi.monpaniervert.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.managers.SiteManager;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.services.SiteService;
import rfi.monpaniervert.exceptions.NotFoundException;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteManager siteManager;

	@Override
	public Site getById(Long id) throws NotFoundException {
		return this.siteManager.getById(id);
	}

	@Override
	public Site put(Long id, Site Site) {
		return this.siteManager.put(id, Site);
	}
	
	@Override
	public Site create(Site Site) {
		return this.siteManager.create(Site);
	}

	@Override
	public void delete(Long id) {
		this.siteManager.delete(id);
		
	}

	@Override
	public Page<Site> find(TdbSiteDTO tdbSiteDTO, Pageable pagination) {
		return this.siteManager.find(tdbSiteDTO, pagination);
	}

}
