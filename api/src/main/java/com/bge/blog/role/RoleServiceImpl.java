package com.bge.blog.role;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role get(long id) {
		Optional<Role> roleOption = roleRepository.findById(id);
		if (roleOption.isEmpty()) {
			throw new NullPointerException();
		} else {
			return roleOption.get();
		}
		
	}
	@Override
	public long save(Role role) {
		return roleRepository.save(role).getId();
	}

}
