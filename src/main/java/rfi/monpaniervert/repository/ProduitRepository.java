package rfi.monpaniervert.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.ESSCategorie;
import rfi.monpaniervert.models.Produit;


@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	

	@Query("SELECT DISTINCT new rfi.monpaniervert.dto.ProduitDTO(p.id, p.name, p.categorie, p.tarif, p.quantite, p.img, c.name, c.id, p.bio, p.promo, p.poidsMin, p.poidsMax, p.nbPieceLot, p.ssCategorie) "
			+ "FROM Produit p LEFT JOIN p.compagnie c "
			+ "WHERE c.id in ?1 AND (p.categorie in ?2) AND (p.ssCategorie in ?3) AND (?4 is NULL OR (lower(p.name) LIKE lower(concat('%', ?4,'%')))) AND (p.quantite is NULL OR p.quantite > 0) "
			+ "AND c.status = 'VALIDE'") 
	Page<ProduitDTO> findDTO( List<Long> idCompagnie, List<ECategorie> categories, List<ESSCategorie> ssCategories, String searchTerm, Pageable pagination);

	@Query("SELECT count(p) FROM Produit p LEFT JOIN p.compagnie c WHERE c.id in ?1 AND ?2 = p.categorie AND (p.quantite is NULL OR p.quantite > 0) AND c.status = 'VALIDE'") 
	Long countByCategorie( List<Long> idCompagnie, ECategorie categorie);

	@Query("SELECT count(p) FROM Produit p LEFT JOIN p.compagnie c WHERE c.id in ?1 AND ?2 = p.ssCategorie AND (p.quantite is NULL OR p.quantite > 0) AND c.status = 'VALIDE'") 
	Long countBySSCategorie( List<Long> idCompagnie, ESSCategorie categorie);
}
			