package rfi.monpaniervert.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import rfi.monpaniervert.dto.ProduitCommandeDTO;
import rfi.monpaniervert.models.ProduitCommande;

@Mapper(componentModel = "spring")
public interface ProduitCommandeMapper extends EntityDtoMapper<ProduitCommande, ProduitCommandeDTO> {

	public ProduitCommandeMapper INSTANCE = Mappers.getMapper(ProduitCommandeMapper.class);

    @Override
    @Mapping(target = "commandeCompagnie", ignore = true)
    ProduitCommandeDTO toDto(ProduitCommande entity);
    
    @Override
    @Mapping(target = "commandeCompagnie", ignore = true)
    ProduitCommande toEntity(ProduitCommandeDTO dto);  

}
