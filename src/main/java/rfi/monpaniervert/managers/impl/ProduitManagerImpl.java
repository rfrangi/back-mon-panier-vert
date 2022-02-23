package rfi.monpaniervert.managers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.dto.TdbProduitDTO;
import rfi.monpaniervert.enums.ECategorie;
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
	public Page<ProduitDTO> find(TdbProduitDTO tdbProduitDTO, Pageable pagination) {
		final String searchTerm = tdbProduitDTO.getSearchTerm() == "" ? null : tdbProduitDTO.getSearchTerm().equals(null) ? null : tdbProduitDTO.getSearchTerm().trim();
		final List<ECategorie> categories = tdbProduitDTO.getCategories()!= null ? tdbProduitDTO.getCategories() : null;
		final Long idCompagnie = tdbProduitDTO.getIdCompagnie();
		return this.produitRepository.findDTO(searchTerm, categories, idCompagnie, pagination);
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
}
