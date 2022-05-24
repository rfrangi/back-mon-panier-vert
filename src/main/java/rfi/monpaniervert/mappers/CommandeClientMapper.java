package rfi.monpaniervert.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import rfi.monpaniervert.dto.CommandeClientDTO;
import rfi.monpaniervert.models.CommandeClient;

@Mapper(componentModel = "spring", uses = { CommandeCompagnieMapper.class, AdresseMapper.class })
public interface CommandeClientMapper extends EntityDtoMapper<CommandeClient, CommandeClientDTO> {
	
	public CommandeClientMapper INSTANCE = Mappers.getMapper(CommandeClientMapper.class);

    @Override
    CommandeClient toEntity(CommandeClientDTO dto);  
    
    @Override
    CommandeClientDTO toDto(CommandeClient entity);
    
}
