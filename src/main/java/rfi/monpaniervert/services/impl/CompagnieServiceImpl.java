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

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.TdbCompagnieDTO;
import rfi.monpaniervert.enums.FileType;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.managers.CompagnieManager;
import rfi.monpaniervert.managers.S3Manager;
import rfi.monpaniervert.mappers.CompagnieMapper;
import rfi.monpaniervert.models.Compagnie;
import rfi.monpaniervert.services.CompagnieService;

@Service
public class CompagnieServiceImpl implements CompagnieService {

	@Autowired private CompagnieManager compagnieManager;
	@Autowired private CompagnieMapper compagnieMapper;
	@Autowired private S3Manager s3Manager;

	private static final Logger LOGGER = LoggerFactory.getLogger(CompagnieServiceImpl.class);

	@Override
	public CompagnieDTO getById(Long id) throws NotFoundException {
		return this.compagnieMapper.toDto(this.compagnieManager.getById(id));
	}

	@Override
	public CompagnieDTO put(Long id, Compagnie compagnie, MultipartFile files) {
		if(files != null) {
			compagnie.setImg(saveMultipartFileOnS3(compagnie.getId(), files, FileType.IMG_COMPAGNIE));
		}
		return this.compagnieMapper.toDto(this.compagnieManager.put(id, compagnie));
	}
	
	@Override
	public CompagnieDTO create(Compagnie compagnie, MultipartFile files) {
		if(files != null) {
			compagnie.setImg(saveMultipartFileOnS3(compagnie.getId(), files, FileType.IMG_COMPAGNIE));
		}
		return this.compagnieMapper.toDto(this.compagnieManager.create(compagnie));
	}

	@Override
	public void delete(Long id) {
		this.compagnieManager.delete(id);
	}

	@Override
	public Page<CompagnieDTO> find(TdbCompagnieDTO tdbCompagnieDTO, Pageable pagination) {
		return this.compagnieManager.find(tdbCompagnieDTO, pagination);
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
