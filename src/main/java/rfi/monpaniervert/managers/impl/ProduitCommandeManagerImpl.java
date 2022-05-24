package rfi.monpaniervert.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.managers.ProduitCommandeManager;
import rfi.monpaniervert.models.ProduitCommande;
import rfi.monpaniervert.repository.ProduitCommandeRepository;

@Component
public class ProduitCommandeManagerImpl  implements ProduitCommandeManager {

	@Autowired
	private ProduitCommandeRepository produitCommandeRepository;

	@Override
	public ProduitCommande save(ProduitCommande produitCommande) {
		return this.produitCommandeRepository.save(produitCommande);
	}
}
