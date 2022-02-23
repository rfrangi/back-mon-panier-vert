package rfi.monpaniervert.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.models.Produit;

@Mapper(componentModel = "spring")
public interface ProduitMapper extends EntityDtoMapper<Produit, ProduitDTO> {

	public ProduitMapper INSTANCE = Mappers.getMapper(ProduitMapper.class);

	
    @Override
    @Mapping(target = "idCompagnie", ignore = true)
    @Mapping(target = "compagnieName", ignore = true)
    ProduitDTO toDto(Produit entity);
    
 
    @Override
    @Mapping(target = "compagnie", ignore = true)
    Produit toEntity(ProduitDTO dto);  
  
    @AfterMapping
    default void changeRole(@MappingTarget ProduitDTO dto, Produit entity) {
    	dto.setIdCompagnie(entity.getCompagnie().getId());
    	dto.setCompagnieName(entity.getCompagnie().getName());
    }	
}
