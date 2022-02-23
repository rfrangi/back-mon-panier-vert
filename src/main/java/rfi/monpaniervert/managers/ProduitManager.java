package rfi.monpaniervert.managers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.dto.TdbProduitDTO;
import rfi.monpaniervert.models.Produit;

public interface ProduitManager {

	Produit add(Produit produit);

	Page<ProduitDTO> find(TdbProduitDTO tdbProduitDTO, Pageable pagination);

	Produit put(Produit produit);

	void delete(Long id);

	Produit getById(Long id);
}