package rfi.monpaniervert.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.models.Compagnie;


@Repository
public interface CompagnieRepository extends JpaRepository<Compagnie, Long> {

	@Query("SELECT DISTINCT c FROM Compagnie c LEFT JOIN c.adresse WHERE c.id = ?1")
	Compagnie getById(Long id);

	/*@Query("SELECT DISTINCT rfi.monpaniervert.dto.CompagnieDTO(c.id, c.status, c.name, c.siret, c.telephone, c.email, c.creationDate, c.img) "
			+ "FROM Compagnie c WHERE (?1 is NULL OR (c.name LIKE ?1))")
	*/
	@Query("SELECT DISTINCT c "
			+ "FROM Compagnie c WHERE (?1 is NULL OR (c.name LIKE ?1))")
	Page<Compagnie> find(String searchTerm, Pageable pagination);

}
			