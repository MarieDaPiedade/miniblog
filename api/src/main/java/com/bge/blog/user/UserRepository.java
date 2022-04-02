package com.bge.blog.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public Optional<User> findByMail(String mail);
	
	public Optional<User> findByUsername(String username);
	
}
