package rfi.monpaniervert.mappers;

import org.mapstruct.Mapper;

import rfi.monpaniervert.dto.AdresseDTO;
import rfi.monpaniervert.models.Adresse;

@Mapper(componentModel = "spring")
public interface AdresseMapper extends EntityDtoMapper<Adresse, AdresseDTO> {

}
