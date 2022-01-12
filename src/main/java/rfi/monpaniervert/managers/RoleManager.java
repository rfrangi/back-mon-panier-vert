package rfi.monpaniervert.managers;

import rfi.monpaniervert.enums.ERole;
import rfi.monpaniervert.models.Role;
import rfi.monpaniervert.exceptions.NotFoundException;

public interface RoleManager {

	Role add(Role role);
	
	Role put(Integer id, Role role);
	
	void delete(Integer id);
		
	Role getById(Integer id) throws NotFoundException;
	
	Role getByName(ERole name) throws NotFoundException;
}
