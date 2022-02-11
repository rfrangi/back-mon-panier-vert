package rfi.monpaniervert.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.enums.EStatusCompagnie;
import rfi.monpaniervert.models.Compagnie;


@Repository
public interface CompagnieRepository extends JpaRepository<Compagnie, Long> {

	@Query("SELECT DISTINCT c FROM Compagnie c LEFT JOIN c.adresse WHERE c.id = ?1")
	Compagnie getById(Long id);

	@Query("SELECT DISTINCT new rfi.monpaniervert.models.Compagnie(c.id, c.name, c.status, c.creationDate, c.modificationDate) "
			+ "FROM Compagnie c "
			+ "WHERE (?1 is NULL OR (c.name LIKE ?1)) "
			+ "AND (c.status in ?2)")
	Page<Compagnie> find(String searchTerm, List<EStatusCompagnie> status, Pageable pagination);
	
	@Query("SELECT DISTINCT new rfi.monpaniervert.dto.CompagnieDTO(c.id, c.name, c.status, c.creationDate, c.modificationDate, c.adresse.idAdresse, c.adresse.ville, c.adresse.codePostal, c.adresse.pays) "
			+ "FROM Compagnie c "
			+ "WHERE (?1 is NULL OR (lower(c.name) LIKE lower(concat('%', ?1,'%')))) "
			+ "AND (c.status in ?2)") 
	Page<CompagnieDTO> findDTO(String searchTerm, List<EStatusCompagnie> status, Pageable pagination);

}
			