package rfi.monpaniervert.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.TdbCompagnieDTO;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.models.Compagnie;

public interface CompagnieService {

	
	CompagnieDTO getById(Long id) throws NotFoundException;
	
	CompagnieDTO put(Long id, Compagnie compagnie, MultipartFile files);
	
	void delete(Long id);
	
	CompagnieDTO create(Compagnie compagnie, MultipartFile files);

	Page<CompagnieDTO> find(TdbCompagnieDTO tdbCompagnieDTO, Pageable pagination);
}
