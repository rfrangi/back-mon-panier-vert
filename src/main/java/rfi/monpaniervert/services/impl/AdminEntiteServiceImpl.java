package rfi.monpaniervert.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.S3ObjectSummary;

import rfi.monpaniervert.managers.AdminEntiteManager;
import rfi.monpaniervert.managers.AdresseManager;
import rfi.monpaniervert.managers.CompagnieManager;
import rfi.monpaniervert.managers.SiteManager;
import rfi.monpaniervert.managers.UserManager;
import rfi.monpaniervert.mappers.CompagnieMapper;
import rfi.monpaniervert.mappers.SiteMapper;
import rfi.monpaniervert.mappers.UserMapper;
import rfi.monpaniervert.models.AdminEntite;
import rfi.monpaniervert.models.Adresse;
import rfi.monpaniervert.models.Site;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.services.AdminEntiteService;
import rfi.monpaniervert.services.AdresseService;
import rfi.monpaniervert.dto.AdminEntiteDTO;
import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.enums.ETypeEntite;
import rfi.monpaniervert.exceptions.NotFoundException;

@Service
public class AdminEntiteServiceImpl implements AdminEntiteService {

	@Autowired private AdminEntiteManager adminEntiteManager;
	@Autowired private UserManager userManager;
	@Autowired private SiteManager siteManager;
	@Autowired private CompagnieManager compagnieManager;

	@Autowired private SiteMapper siteMapper;
	@Autowired private CompagnieMapper compagnieMapper;
	@Autowired private UserMapper userMapper;

	@Override
	public AdminEntite post(AdminEntite adminEntite) {
		final Long idUser = adminEntite.getUserId();
		final User user = userManager.getById(idUser);
		//TODO: Changer le role du user
		return this.adminEntiteManager.post(adminEntite);
	}


	@Override
	public List<SiteDTO> getSiteByUser(Long id) {
		final List<AdminEntite> list = this.adminEntiteManager.getByUserIdAndType(id, ETypeEntite.SITE);
		return list.stream().map(AdminEntite::getEntiteId).map(idSite -> this.siteMapper.toDto(this.siteManager.getById(idSite))).collect(Collectors.toList());
	}


	@Override
	public List<CompagnieDTO> getCompagnieByUser(Long id) {
		final List<AdminEntite> list = this.adminEntiteManager.getByUserIdAndType(id, ETypeEntite.SITE);
		return list.stream().map(AdminEntite::getEntiteId).map(idSite -> this.compagnieMapper.toDto(this.compagnieManager.getById(idSite))).collect(Collectors.toList());
	}


	@Override
	public List<UserDTO> getAdminForEntite(Long id, ETypeEntite type) {
		if (ETypeEntite.SITE.equals(type)) {
			final List<AdminEntite> list = this.adminEntiteManager.getByEntiteIdAndType(id, ETypeEntite.SITE);
			return list.stream().map(AdminEntite::getUserId).map(idUser -> this.userMapper.toDto(this.userManager.getById(idUser))).collect(Collectors.toList());
		}
		
		if (ETypeEntite.COMPAGNIE.equals(type)) {
			final List<AdminEntite> list = this.adminEntiteManager.getByEntiteIdAndType(id, ETypeEntite.COMPAGNIE);
			return list.stream().map(AdminEntite::getUserId).map(idUser -> this.userMapper.toDto(this.userManager.getById(idUser))).collect(Collectors.toList());
		}

		throw new NotFoundException("Type Inconnu");
	}



	@Override
	public void delete(Long id, Long idEntite, ETypeEntite type) {}
}
