package rfi.monpaniervert.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.dto.TdbSiteDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.managers.CompagnieManager;
import rfi.monpaniervert.managers.SiteManager;
import rfi.monpaniervert.mappers.CompagnieMapper;
import rfi.monpaniervert.mappers.SiteMapper;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.services.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired private SiteManager siteManager;
	@Autowired private CompagnieManager compagnieManager;

	@Autowired private SiteMapper siteMapper;
	@Autowired private CompagnieMapper compagnieMapper;

	//private static final Logger LOGGER = LoggerFactory.getLogger(CompagnieServiceImpl.class);


	@Override
	public SiteDTO getById(Long id) throws NotFoundException {
		return this.siteMapper.toDto(this.siteManager.getById(id));
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
	public Page<SiteDTO> find(TdbSiteDTO tdbSiteDTO, Pageable pagination) {
		return this.siteManager.find(tdbSiteDTO, pagination);
	}

	@Override
	public List<CompagnieDTO> findCompagnies(Long id) {
		return this.compagnieMapper.toDtoList(siteManager.findCompagnies(id));
	}

	@Override
	public SiteDTO addCompagnie(Long id, Long idCompagnie) {
		final Compagnie compagnie = this.compagnieManager.getById(idCompagnie);
		final Site site = this.siteManager.addCompagnie(id, compagnie);				
		return this.siteMapper.toDto(site);
	}

	@Override
	public SiteDTO addCompagnies(Long id, List<CompagnieDTO> compagniesDTO) {
		final List<Compagnie> compagnies = compagniesDTO.stream().map(dto -> this.compagnieManager.getById(dto.getId())).toList();
		final Site site = this.siteManager.addCompagnies(id, compagnies);
		return this.siteMapper.toDto(site);
	}

	@Override
	public void removeCompagnie(Long id, Long idCompagnie) {
		this.siteManager.removeCompagnie(id, idCompagnie);
	}

}
