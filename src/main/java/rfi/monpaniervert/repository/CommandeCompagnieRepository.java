package rfi.monpaniervert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.models.CommandeCompagnie;


@Repository
public interface CommandeCompagnieRepository extends JpaRepository<CommandeCompagnie, Long> {

}
