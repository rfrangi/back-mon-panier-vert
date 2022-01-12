package rfi.monpaniervert.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	User getById(Long id);

	User getByEmail(String email);

	@Query("SELECT DISTINCT new rfi.monpaniervert.dto.UserDTO(u.id, u.email, u.lastname, u.firstname, u.civilite, u.creationDate, u.status) "
			+ "FROM User u  WHERE (?1 is NULL OR (lower(u.email) LIKE lower(concat('%', ?1,'%'))))")
	Page<UserDTO> find(String searchTerm, Pageable pagination);

}
