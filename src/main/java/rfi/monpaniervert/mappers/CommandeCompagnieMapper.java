package rfi.monpaniervert.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import rfi.monpaniervert.dto.CommandeCompagnieDTO;
import rfi.monpaniervert.models.CommandeCompagnie;

@Mapper(componentModel = "spring", uses = { ProduitCommandeMapper.class, AdresseMapper.class })
public interface CommandeCompagnieMapper extends EntityDtoMapper<CommandeCompagnie, CommandeCompagnieDTO> {
	
	public CommandeCompagnieMapper INSTANCE = Mappers.getMapper(CommandeCompagnieMapper.class);

    @Override
    @Mapping(target = "commandeClient", ignore = true)
    CommandeCompagnie toEntity(CommandeCompagnieDTO dto);  
    
    @Override
    @Mapping(target = "commandeClientId", ignore = true)
    CommandeCompagnieDTO toDto(CommandeCompagnie entity);
    
    @AfterMapping
    default void changeCompagnie(@MappingTarget CommandeCompagnieDTO dto, CommandeCompagnie entity) {
    	dto.setCommandeClientId(entity.getId());
    }	
}
