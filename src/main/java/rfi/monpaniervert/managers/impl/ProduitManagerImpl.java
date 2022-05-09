package rfi.monpaniervert.managers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.ESSCategorie;
import rfi.monpaniervert.managers.ProduitManager;
import rfi.monpaniervert.models.Produit;
import rfi.monpaniervert.repository.ProduitRepository;

@Component
public class ProduitManagerImpl  implements ProduitManager {

	@Autowired private ProduitRepository produitRepository;

	@Override
	public Produit add(Produit produit) {
		return this.produitRepository.save(produit);
	}

	@Override
	public Page<ProduitDTO> find(String searchTerm, List<ECategorie> categories, List<Long> idsCompagnie, List<ESSCategorie> ssCategories, Pageable pagination) {
		return this.produitRepository.findDTO( idsCompagnie, categories, ssCategories, searchTerm, pagination);
	}

	@Override
	public Produit put(Produit produit) {
		return this.produitRepository.save(produit);
	}

	@Override
	public void delete(Long id) {
		this.produitRepository.deleteById(id);
		
	}

	@Override
	public Produit getById(Long id) {
		return this.produitRepository.getById(id);
	}

	@Override
	public long  countByCategorie(ECategorie categorie, List<Long> idsCompagnie) {
		return this.produitRepository.countByCategorie(idsCompagnie, categorie);
	}

	@Override
	public long countBySSCategorie(ESSCategorie ssCategorie, List<Long> idsCompagnie) {
		return this.produitRepository.countBySSCategorie(idsCompagnie, ssCategorie);

	}
}
