package rfi.monpaniervert.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.models.Site;


@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
	
	Site getById(Long id);

	@Query("SELECT DISTINCT s FROM Site s WHERE (?1 is NULL OR (s.name LIKE ?1))")
	Page<Site> find(String searchTerm, Pageable pagination);

}
