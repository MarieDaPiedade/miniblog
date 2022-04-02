package com.bge.blog.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

	Optional<Post> findByTitle(String string);
	
	List<Post> findAllByIsModerated(boolean isModerated);

}
