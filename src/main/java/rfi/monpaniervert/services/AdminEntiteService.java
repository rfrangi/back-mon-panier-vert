package rfi.monpaniervert.services;

import java.util.List;

import rfi.monpaniervert.dto.CompagnieDTO;
import rfi.monpaniervert.dto.SiteDTO;
import rfi.monpaniervert.dto.UserDTO;
import rfi.monpaniervert.enums.ETypeEntite;
import rfi.monpaniervert.models.AdminEntite;

public interface AdminEntiteService {

	AdminEntite post(AdminEntite adminEntite);

	List<SiteDTO> getSiteByUser(Long id);

	List<CompagnieDTO> getCompagnieByUser(Long id);

	List<UserDTO> getAdminForEntite(Long id, ETypeEntite type);

	void delete(Long id, Long idEntite, ETypeEntite type);


}
