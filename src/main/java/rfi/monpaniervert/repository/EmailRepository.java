package rfi.monpaniervert.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.models.Email;


@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

	@Query("SELECT DISTINCT e "
			+ "FROM Email e WHERE (?1 is NULL OR (lower(e.dest) LIKE lower(concat('%', ?1,'%'))))")
	Page<Email> find(String searchTerm, Pageable pagination);

}
			