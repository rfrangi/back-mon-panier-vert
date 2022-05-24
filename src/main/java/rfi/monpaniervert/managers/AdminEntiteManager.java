package rfi.monpaniervert.managers;

import java.util.List;

import rfi.monpaniervert.enums.ETypeEntite;
import rfi.monpaniervert.models.AdminEntite;

public interface AdminEntiteManager {

	AdminEntite post(AdminEntite adminEntite);

	void delete(Long id);

	List<AdminEntite> getByUserIdAndType(Long id, ETypeEntite site);

	List<AdminEntite> getByEntiteIdAndType(Long id, ETypeEntite type);
	
	

}
