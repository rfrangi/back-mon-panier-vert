package rfi.monpaniervert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.models.ProduitCommande;


@Repository
public interface ProduitCommandeRepository extends JpaRepository<ProduitCommande, Long> {

}
