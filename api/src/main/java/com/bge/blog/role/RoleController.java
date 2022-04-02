package com.bge.blog.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/* CREATE */
	/* Not Yet
	@PostMapping("/save")
	public long saveRole(@RequestParam("role") Role role) {
		return roleService.save(role);
	}*/
	
	/* READ */
	
	@GetMapping("/get/{id}")
	public Role getRole(@PathVariable("id") String id) {
		try {
			return roleService.get(Long.parseLong(id));
		} catch (Exception e) {
			return null;
		}
	}
	
}
