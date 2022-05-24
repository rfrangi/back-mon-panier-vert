package rfi.monpaniervert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.enums.ETypeEntite;
import rfi.monpaniervert.models.AdminEntite;


@Repository
public interface AdminEntiteRepository extends JpaRepository<AdminEntite, Long> {

	List<AdminEntite> getByUserIdAndType(Long userId, ETypeEntite type);

	List<AdminEntite> getByEntiteIdAndType(Long id, ETypeEntite type);

}
