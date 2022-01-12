package rfi.monpaniervert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rfi.monpaniervert.models.Adresse;


@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

	Optional<Adresse> getByIdAdresse(Long id);
}
