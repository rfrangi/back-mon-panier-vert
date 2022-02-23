package rfi.monpaniervert.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.dto.TdbProduitDTO;

public interface ProduitService {

	ProduitDTO add(ProduitDTO produitDTO, MultipartFile files);

	Page<ProduitDTO> find(TdbProduitDTO tdbProduitDTO, Pageable pagination);

	ProduitDTO put(ProduitDTO produitDTO, MultipartFile files);

	void delete(Long id);

	ProduitDTO getById(Long id);
}
