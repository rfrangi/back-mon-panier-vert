package rfi.monpaniervert.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rfi.monpaniervert.dto.ProduitByCatDTO;
import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.dto.TdbProduitDTO;
import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.ESSCategorie;
import rfi.monpaniervert.enums.FileType;
import rfi.monpaniervert.managers.CompagnieManager;
import rfi.monpaniervert.managers.ProduitManager;
import rfi.monpaniervert.managers.S3Manager;
import rfi.monpaniervert.managers.SiteManager;
import rfi.monpaniervert.mappers.ProduitMapper;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.Produit;
import rfi.monpaniervert.services.ProduitService;

@Service
public class ProduitServiceImpl implements ProduitService {
	
	@Autowired private CompagnieManager compagnieManager;
	@Autowired private SiteManager siteManager;
	@Autowired private ProduitManager produitManager;
	@Autowired private ProduitMapper produitMapper;
	@Autowired private S3Manager s3Manager;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitServiceImpl.class);

	
	@Override
	public ProduitDTO add(ProduitDTO produitDTO, MultipartFile files) {
		final Compagnie compagnie = this.compagnieManager.getById(produitDTO.getIdCompagnie());
		if(files != null) {
			produitDTO.setImg(saveMultipartFileOnS3(compagnie.getId(), files, FileType.IMG_PRODUIT));
		}
		final Produit produit = this.produitMapper.toEntity(produitDTO);
		produit.setCompagnie(compagnie);
	    final Produit produitBDD = this.produitManager.add(produit);
		return this.produitMapper.toDto(produitBDD);
	}

	@Override
	public Page<ProduitDTO> find(TdbProduitDTO tdbProduitDTO, Pageable pagination) {
		final String searchTerm = tdbProduitDTO.getSearchTerm() == "" || tdbProduitDTO.getSearchTerm() == null ? null : tdbProduitDTO.getSearchTerm().equals(null) ? null : tdbProduitDTO.getSearchTerm().trim();
		final List<ECategorie> categories = tdbProduitDTO.getCategories()!= null ? tdbProduitDTO.getCategories() : null;
		final List<ESSCategorie> ssCategories = tdbProduitDTO.getSsCategories() != null ? tdbProduitDTO.getSsCategories() : null;
		final List<Long> idsCompagnie = this.getIdsCompagnies(tdbProduitDTO);
		return this.produitManager.find(searchTerm, categories, idsCompagnie, ssCategories, pagination);
	}
	
	@Override
	public ProduitByCatDTO findByCategorie(TdbProduitDTO tdbProduitDTO, Pageable pagination) {
		final ProduitByCatDTO result = new ProduitByCatDTO();
		final String searchTerm = tdbProduitDTO.getSearchTerm() == "" || tdbProduitDTO.getSearchTerm() == null ? null : tdbProduitDTO.getSearchTerm().equals(null) ? null : tdbProduitDTO.getSearchTerm().trim();
		final List<ECategorie> categories = tdbProduitDTO.getCategories()!= null ? tdbProduitDTO.getCategories() : null;
		final List<ESSCategorie> ssCategories = tdbProduitDTO.getSsCategories() != null ? tdbProduitDTO.getSsCategories() : null;
		final List<Long> idsCompagnie = this.getIdsCompagnies(tdbProduitDTO);
		
		final Map<ESSCategorie, Long> mapNbSSCategorie = new HashMap<ESSCategorie, Long>();
		for(final ESSCategorie cat :tdbProduitDTO.getSsCategories()) {
			final Long nbByCat = this.produitManager.countBySSCategorie(cat, idsCompagnie);
			mapNbSSCategorie.put(cat, nbByCat);
		}
		result.setMapNbSSCategorie(mapNbSSCategorie);
		result.setResult(this.produitManager.find(searchTerm, categories, idsCompagnie, ssCategories, pagination));
		return result;
	}
	
	private List<Long> getIdsCompagnies(TdbProduitDTO tdbProduitDTO) {
		final List<Long> idsCompagnie = new ArrayList<Long>();
		if(tdbProduitDTO.getIdCompagnie() != null) {
			idsCompagnie.add(tdbProduitDTO.getIdCompagnie());
		}
		
		if(tdbProduitDTO.getIdSite() != null) {
			final List<Long> idsCompagnieSite = siteManager.getById(tdbProduitDTO.getIdSite()).getCompagnies().stream().map(c -> c.getId()).collect(Collectors.toList());
			idsCompagnie.addAll(idsCompagnieSite);
		}
		
		return idsCompagnie;
	}

	@Override
	public ProduitDTO put(ProduitDTO produitDTO, MultipartFile files) {
		final Compagnie compagnie = this.compagnieManager.getById(produitDTO.getIdCompagnie());
		if(files != null) {
			produitDTO.setImg(saveMultipartFileOnS3(compagnie.getId(), files, FileType.IMG_PRODUIT));
		}
		final Produit produit = this.produitMapper.toEntity(produitDTO);
		produit.setCompagnie(compagnie);
	    final Produit produitBDD = this.produitManager.put(produit);
		return this.produitMapper.toDto(produitBDD);
	}



	@Override
	public void delete(Long id) {
		this.produitManager.delete(id);
	}



	@Override
	public ProduitDTO getById(Long id) {
		return this.produitMapper.toDto(this.produitManager.getById(id));
	}
	
	private String saveMultipartFileOnS3(Long id, MultipartFile multipartFile, FileType type) {
		try {
			final File tempFile = File.createTempFile(multipartFile.getOriginalFilename(), null);
	    	final int i = multipartFile.getOriginalFilename().lastIndexOf('.');
	    	final String ext =  i > 0 ? "."+multipartFile.getOriginalFilename().substring(i+1) : "";
			FileUtils.writeByteArrayToFile(tempFile, multipartFile.getBytes());
			final String url = this.s3Manager.putFile(tempFile, id != null ? id.toString(): "" + ext, type);
			tempFile.deleteOnExit();
			return url;

		} catch (IOException e) {
			LOGGER.error("Error while saving file on S3 {}", multipartFile.getOriginalFilename());
			throw new RuntimeException();
		}
	}


}
