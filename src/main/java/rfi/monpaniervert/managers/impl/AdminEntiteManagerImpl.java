package rfi.monpaniervert.managers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.enums.ETypeEntite;
import rfi.monpaniervert.managers.AdminEntiteManager;
import rfi.monpaniervert.models.AdminEntite;
import rfi.monpaniervert.repository.AdminEntiteRepository;

@Component
public class AdminEntiteManagerImpl  implements AdminEntiteManager {

	@Autowired private AdminEntiteRepository adminEntiteRepository;

	@Override
	public AdminEntite post(AdminEntite adminEntite) {
		return this.adminEntiteRepository.save(adminEntite);
	}

	@Override
	public void delete(Long id) {
		this.adminEntiteRepository.deleteById(id);		
	}

	@Override
	public List<AdminEntite> getByUserIdAndType(Long id, ETypeEntite type) {
		return this.adminEntiteRepository.getByUserIdAndType(id, type);

	}

	@Override
	public List<AdminEntite> getByEntiteIdAndType(Long id, ETypeEntite type) {
		return this.adminEntiteRepository.getByEntiteIdAndType(id, type);
	}
}
