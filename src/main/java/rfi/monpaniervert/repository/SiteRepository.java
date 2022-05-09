package rfi.monpaniervert.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.enums.EStatusSite;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.Site;


@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
	
	@Query("SELECT DISTINCT s FROM Site s LEFT JOIN s.adresse WHERE s.id = ?1")
	Site getById(Long id);

	@Query("SELECT DISTINCT new rfi.monpaniervert.dto.SiteDTO(s.id, s.name, s.status, s.creationDate, s.modificationDate, s.adresse.idAdresse, s.adresse.adresse, s.adresse.ville, s.adresse.codePostal, s.adresse.pays, s.email, s.telephone) "
			+ "FROM Site s "
			+ "WHERE (?1 is NULL OR (lower(s.name) LIKE lower(concat('%', ?1,'%')))) "
			+ "AND (s.status in ?3) "
			+ "AND (?2 is NULL OR (lower(s.adresse.ville) LIKE lower(concat('%', ?2,'%'))))")
	Page<SiteDTO> find(String searchTerm, String searchByAdresse, List<EStatusSite> status, Pageable pagination);

	@Query("SELECT DISTINCT s.compagnies FROM Site s WHERE s.id = ?1")
    List<Compagnie> findCompagnies(Long id);

	@Query("SELECT DISTINCT s FROM Site s LEFT JOIN s.adresse WHERE s.id = ?1")
	Site getByIdWhithCompagnies(Long id);

}
