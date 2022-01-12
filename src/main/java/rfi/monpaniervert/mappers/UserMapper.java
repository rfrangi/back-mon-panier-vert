package rfi.monpaniervert.mappers;

import java.util.HashSet;
import java.util.Objects;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.models.User;

@Mapper
public interface UserMapper extends EntityDtoMapper<User, UserDTO> {

	public UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Override
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserDTO toDto(User entity);
    
 
    @Override
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserDTO dto);  
    
    @AfterMapping
    default void changeRole(@MappingTarget UserDTO dto, User entity) {
    	dto.setRoles(new HashSet<String>());
    	entity.getRoles().stream().filter(Objects::nonNull).forEach(role -> {
    		dto.getRoles().add(role.getName().toString());
    	});
    }	
}
