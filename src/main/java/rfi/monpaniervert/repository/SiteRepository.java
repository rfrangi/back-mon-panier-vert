package rfi.monpaniervert.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.Site;


@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
	
	@Query("SELECT DISTINCT s FROM Site s LEFT JOIN s.adresse WHERE s.id = ?1")
	Site getById(Long id);

	@Query("SELECT DISTINCT new rfi.monpaniervert.models.Site(s.id, s.name, s.status, s.creationDate, s.modificationDate) "
			+ "FROM Site s "
			+ "WHERE (?1 is NULL OR (lower(s.name) LIKE lower(concat('%', ?1,'%'))))")
	Page<Site> find(String searchTerm, Pageable pagination);

	@Query("SELECT DISTINCT s.compagnies FROM Site s WHERE s.id = ?1")
    List<Compagnie> findCompagnies(Long id);

	@Query("SELECT DISTINCT s FROM Site s LEFT JOIN s.adresse WHERE s.id = ?1")
	Site getByIdWhithCompagnies(Long id);

}
