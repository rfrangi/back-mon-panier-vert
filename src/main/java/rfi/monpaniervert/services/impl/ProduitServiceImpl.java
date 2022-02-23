package rfi.monpaniervert.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.dto.TdbProduitDTO;
import rfi.monpaniervert.managers.CompagnieManager;
import rfi.monpaniervert.managers.ProduitManager;
import rfi.monpaniervert.mappers.ProduitMapper;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.Produit;
import rfi.monpaniervert.services.ProduitService;

@Service
public class ProduitServiceImpl implements ProduitService {
	
	@Autowired private CompagnieManager compagnieManager;
	@Autowired private ProduitManager produitManager;
	@Autowired private ProduitMapper produitMapper;
	
	@Override
	public ProduitDTO add(ProduitDTO produitDTO, MultipartFile files) {
		final Compagnie compagnie = this.compagnieManager.getById(produitDTO.getIdCompagnie());
		final Produit produit = this.produitMapper.toEntity(produitDTO);
		produit.setCompagnie(compagnie);
	    final Produit produitBDD = this.produitManager.add(produit);
		return this.produitMapper.toDto(produitBDD);
	}

	@Override
	public Page<ProduitDTO> find(TdbProduitDTO tdbProduitDTO, Pageable pagination) {
		return this.produitManager.find(tdbProduitDTO, pagination);
	}

	@Override
	public ProduitDTO put(ProduitDTO produitDTO, MultipartFile files) {
		final Compagnie compagnie = this.compagnieManager.getById(produitDTO.getIdCompagnie());
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
}
