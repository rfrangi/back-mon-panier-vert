package rfi.monpaniervert.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.dto.CommandeClientDTO;
import rfi.monpaniervert.enums.EStatusCommande;
import rfi.monpaniervert.models.CommandeClient;


@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long> {

	@Query("SELECT DISTINCT new rfi.monpaniervert.dto.CommandeClientDTO(cmdClient.id, cmdClient.creationDate) "
			+ "FROM CommandeClient cmdClient LEFT JOIN cmdClient.commandesCompagnie cmdComp "
			+ "WHERE (?1 is NULL OR cmdClient.userId = ?1) "
			+ "AND (?2 is NULL OR cmdClient.siteId = ?2) "
			+ "AND (cmdClient.status in ?3) "
			+ "AND (?4 is NULL OR cmdComp.compagnieId = ?4) ") 
	Page<CommandeClientDTO> find(Long userId, Long siteId, List<EStatusCommande> status, Long compagnieId,
			Pageable pagination);
	
	Optional<CommandeClient> findById(Long id);
}
