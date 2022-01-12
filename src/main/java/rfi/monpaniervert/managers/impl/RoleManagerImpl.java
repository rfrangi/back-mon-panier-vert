package rfi.monpaniervert.managers.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rfi.monpaniervert.enums.ERole;
import rfi.monpaniervert.managers.RoleManager;
import rfi.monpaniervert.models.Role;
import rfi.monpaniervert.repository.RoleRepository;
import rfi.monpaniervert.exceptions.NotFoundException;

@Component
public class RoleManagerImpl  implements RoleManager {

	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
	public Role add(Role role) {
		return this.roleRepository.save(role);
	}

	@Override
	public Role getById(Integer id) throws NotFoundException {
        return this.roleRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Role with id: {0} couldn't be found", id)));
	}

	@Override
	public Role put(Integer id, Role role) {
		role.setId(id);
		return this.roleRepository.save(role);
	}

	@Override
	public void delete(Integer id) {
		this.roleRepository.deleteById(id.longValue());
		
	}

	@Override
	public Role getByName(ERole name) throws NotFoundException {
		return this.roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Role with name: {0} couldn't be found", name.toString())));

	}

	
}
