package rfi.monpaniervert.services.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rfi.monpaniervert.dto.ProduitDTO;
import rfi.monpaniervert.dto.TdbProduitDTO;
import rfi.monpaniervert.enums.FileType;
import rfi.monpaniervert.managers.CompagnieManager;
import rfi.monpaniervert.managers.ProduitManager;
import rfi.monpaniervert.managers.S3Manager;
import rfi.monpaniervert.mappers.ProduitMapper;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.models.Produit;
import rfi.monpaniervert.services.ProduitService;

@Service
public class ProduitServiceImpl implements ProduitService {
	
	@Autowired private CompagnieManager compagnieManager;
	@Autowired private ProduitManager produitManager;
	@Autowired private ProduitMapper produitMapper;
	@Autowired private S3Manager s3Manager;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitServiceImpl.class);

	
	@Override
	public ProduitDTO add(ProduitDTO produitDTO, MultipartFile files) {
		final Compagnie compagnie = this.compagnieManager.getById(produitDTO.getIdCompagnie());
		if(files != null) {
			produitDTO.setImg(saveMultipartFileOnS3(compagnie.getId(), files, FileType.IMG_PRODUIT));
		}
		final Produit produit = this.produitMapper.toEntity(produitDTO);
		produit.setCompagnie(compagnie);
	    final Produit produitBDD = this.produitManager.add(produit);
		return this.produitMapper.toDto(produitBDD);
	}

	@Override
	public Page<ProduitDTO> find(TdbProduitDTO tdbProduitDTO, Pageable pagination) {
		return this.produitManager.find(tdbProduitDTO, pagination);
	}

	@Override
	public ProduitDTO put(ProduitDTO produitDTO, MultipartFile files) {
		final Compagnie compagnie = this.compagnieManager.getById(produitDTO.getIdCompagnie());
		if(files != null) {
			produitDTO.setImg(saveMultipartFileOnS3(compagnie.getId(), files, FileType.IMG_PRODUIT));
		}
		final Produit produit = this.produitMapper.toEntity(produitDTO);
		produit.setCompagnie(compagnie);
	    final Produit produitBDD = this.produitManager.put(produit);
		return this.produitMapper.toDto(produitBDD);
	}



	@Override
	public void delete(Long id) {
		this.produitManager.delete(id);
	}



	@Override
	public ProduitDTO getById(Long id) {
		return this.produitMapper.toDto(this.produitManager.getById(id));
	}
	
	private String saveMultipartFileOnS3(Long id, MultipartFile multipartFile, FileType type) {
		try {
			final File tempFile = File.createTempFile(multipartFile.getOriginalFilename(), null);
	    	final int i = multipartFile.getOriginalFilename().lastIndexOf('.');
	    	final String ext =  i > 0 ? "."+multipartFile.getOriginalFilename().substring(i+1) : "";
			FileUtils.writeByteArrayToFile(tempFile, multipartFile.getBytes());
			final String url = this.s3Manager.putFile(tempFile, id != null ? id.toString(): "" + ext, type);
			tempFile.deleteOnExit();
			return url;

		} catch (IOException e) {
			LOGGER.error("Error while saving file on S3 {}", multipartFile.getOriginalFilename());
			throw new RuntimeException();
		}
	}
}
