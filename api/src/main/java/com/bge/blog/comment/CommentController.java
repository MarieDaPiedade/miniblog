package com.bge.blog.comment;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	/* CREATE */
	
	@PostMapping("/save")
	public long saveComment(@RequestBody CommentDTO commentDto) {
		commentDto.setModerated(false);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
		Set<ConstraintViolation<Comment>> constraintViolations = validator.validate(commentService.dtoToComment(commentDto));
		if (constraintViolations.isEmpty()) {
			return commentService.save(commentService.dtoToComment(commentDto));
		} else {
			Map<String, String> errors = new HashMap<>();
			for (ConstraintViolation<?> c : constraintViolations) {
				errors.put(c.getPropertyPath().toString(), c.getMessage());
			}
			return -1; // TODO envoyer le message au front
		}
		
	}
	
	/* READ */
	
	@GetMapping("/")
	public List<CommentDTO> getAllComments() {
		List<CommentDTO> lcdto = new ArrayList<>();
		for(Comment c : commentService.getAll()) {
			lcdto.add(commentService.commentToDto(c));
		}
		return lcdto;
	}
	
	@GetMapping("/get/{id}")
	public CommentDTO getComment(@PathVariable("id") long id) {
		try {
			return commentService.commentToDto(commentService.get(id));
		} catch (Exception e) {
			return null;
		}
	}
	
	/* UPDATE */
	
	@PutMapping("/update/{id}")
	public boolean updateComment(@RequestBody CommentDTO commentDto, @PathVariable("id") long id) {
		try {
			commentService.update(id, commentService.dtoToComment(commentDto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/* DELETE */
	
	@DeleteMapping("/delete/{id}")
	public boolean deleteComment(@PathVariable("id") long id) {
		try {
			commentService.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
