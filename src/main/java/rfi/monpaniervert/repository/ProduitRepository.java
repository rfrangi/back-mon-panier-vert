package rfi.monpaniervert.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.models.Produit;


@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	@Query("SELECT DISTINCT new rfi.monpaniervert.dto.ProduitDTO(p.id, p.name, p.categorie, p.tarif, p.quantite, p.img) "
			+ "FROM Produit p LEFT JOIN p.compagnie c "
			+ "WHERE (?1 is NULL OR (lower(p.name) LIKE lower(concat('%', ?1,'%')))) "
			+ "AND (p.categorie in ?2) "
			+ "AND c.id = ?3") 
	Page<ProduitDTO> findDTO(String searchTerm, List<ECategorie> categories, Long idCompagnie, Pageable pagination);


}
			