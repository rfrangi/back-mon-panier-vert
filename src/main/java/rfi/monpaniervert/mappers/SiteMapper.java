package rfi.monpaniervert.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.models.Site;

@Mapper(componentModel = "spring", uses = CompagnieMapper.class)
public interface SiteMapper extends EntityDtoMapper<Site, SiteDTO> {
	public SiteMapper INSTANCE = Mappers.getMapper(SiteMapper.class);

	 
    @Override
    Site toEntity(SiteDTO dto);  
    
    @Override
    SiteDTO toDto(Site entity);
    
}
