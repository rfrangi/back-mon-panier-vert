package rfi.monpaniervert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.enums.ERole;
import rfi.monpaniervert.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

	Optional<Role> getById(Integer id);
}
