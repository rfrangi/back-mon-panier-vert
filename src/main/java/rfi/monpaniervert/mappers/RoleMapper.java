package rfi.monpaniervert.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import rfi.monpaniervert.dto.RoleDto;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityDtoMapper<RoleDto, RoleDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    RoleDto toDto(RoleDto entity);
}
