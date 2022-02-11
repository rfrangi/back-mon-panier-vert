package rfi.monpaniervert.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.models.Compagnie;

@Mapper(componentModel = "spring")
public interface CompagnieMapper extends EntityDtoMapper<Compagnie, CompagnieDTO> {

	public CompagnieMapper INSTANCE = Mappers.getMapper(CompagnieMapper.class);

    @Override
    CompagnieDTO toDto(Compagnie entity);
    
 
    @Override
    Compagnie toEntity(CompagnieDTO dto);  
    
   /* @AfterMapping
    default void changeRole(@MappingTarget UserDTO dto, User entity) {
    	dto.setRoles(new HashSet<String>());
    	entity.getRoles().stream().filter(Objects::nonNull).forEach(role -> {
    		dto.getRoles().add(role.getName().toString());
    	});
    }	*/
}
