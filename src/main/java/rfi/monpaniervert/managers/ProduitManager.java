package rfi.monpaniervert.managers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.ProduitCommandeDTO;
import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.ESSCategorie;
import rfi.monpaniervert.models.Produit;

public interface ProduitManager {

	Produit add(Produit produit);

	Page<ProduitDTO> find(String searchTerm, List<ECategorie> categories, List<Long> idsCompagnie,List<ESSCategorie> ssCategories, Pageable pagination);

	Produit put(Produit produit);

	void delete(Long id);

	Produit getById(Long id);
	
	long countByCategorie(ECategorie categorie, List<Long> idsCompagnie);
	
	long countBySSCategorie(ESSCategorie ssCategorie, List<Long> idsCompagnie);
	
	Long getQuantiteById(Long id);

	void updateQuantiteProduit(List<ProduitCommandeDTO> produitsCommande);

}