package com.bge.blog.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	/* READ */
	
	@GetMapping("/posts")
	public List<PostDTO> getAllPosts() {
		List<PostDTO> posts = new ArrayList<>();
		for(Post p : postService.getAll(null)) {
			posts.add(postService.postToDto(p));
		}
		
		return posts;
	}
	
	@GetMapping("/posts/connected")
	public List<PostDTO> getAllPostsConnected(Authentication auth) {
		List<PostDTO> posts = new ArrayList<>();
		String username = auth.getName();
		System.out.println(username);
		for(Post p : postService.getAll(username)) {
			posts.add(postService.postToDto(p));
		}
		
		return posts;
	}
	
	@GetMapping("/posts/get/{id}")
	public PostDTO getPost(@PathVariable("id") long id) {
		try {
			return postService.postToDto(postService.get(id));
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/* CREATE */
	
	@PostMapping("/posts/save")
	public long savePost(@RequestBody PostDTO postDTO) {
		postDTO.setModerated(false);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
		Set<ConstraintViolation<Post>> constraintViolations = validator.validate(postService.dtoToPost(postDTO));
		if (constraintViolations.isEmpty()) {
			return postService.save(postService.dtoToPost(postDTO));
		} else {
			Map<String, String> errors = new HashMap<>();
			for (ConstraintViolation<?> c : constraintViolations) {
				errors.put(c.getPropertyPath().toString(), c.getMessage());
			}
			return -1; // TODO envoyer le message au front
		}
	}
	
	
	
	/* UPDATE */
	
	@PutMapping("/posts/update/{id}")
	public boolean updatePost(@RequestBody PostDTO post,@PathVariable("id") long id) {
		try {
			postService.update(id, postService.dtoToPost(post));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/* DELETE */
	
	@DeleteMapping("/posts/delete/{id}")
	public boolean deletePost(@PathVariable("id") String id) {
		try {
			postService.delete(Long.parseLong(id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
