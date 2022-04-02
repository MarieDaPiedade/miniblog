package com.bge.blog.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bge.blog.exception.UserAlreadyExistsException;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/* CREATE */

	@PostMapping("/save")
	public long saveUser(@RequestBody UserDTO userDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(userService.dtoToUser(userDto));

		if (constraintViolations.isEmpty()) {
			try {
				return userService.save(userService.dtoToUser(userDto));
			} catch (UserAlreadyExistsException e) {
				return -1; // TODO envoyer le message au front
			}
		} else {
			Map<String, String> errors = new HashMap<>();
			for (ConstraintViolation<?> c : constraintViolations) {
				errors.put(c.getPropertyPath().toString(), c.getMessage());
			}
			return -2; // TODO envoyer le message au front
		}
	}

	/* READ */

	@GetMapping("/get/{id}")
	public UserDTO getUser(@PathVariable("id") String id) {
		try {
			return userService.userToDto(userService.get(Long.parseLong(id)));
		} catch (Exception e) {
			return null;
		}
	}

}
