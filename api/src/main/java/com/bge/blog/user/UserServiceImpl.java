package com.bge.blog.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bge.blog.exception.UserAlreadyExistsException;
import com.bge.blog.role.Role;
import com.bge.blog.role.RoleRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User get(long id) {
		Optional<User> userOption = userRepository.findById(id);
		if (userOption.isEmpty()) {
			throw new NullPointerException();
		} else {
			return userOption.get();
		}
	}

	@Override
	public long save(User user) throws UserAlreadyExistsException {
		Optional<User> isMailExists = userRepository.findByMail(user.getMail());
		Optional<User> isUsernameExists = userRepository.findByUsername(user.getUsername());
		if (isMailExists.isPresent()) {
			throw new UserAlreadyExistsException("Mail already exists");
		} else if (isUsernameExists.isPresent()) {
			throw new UserAlreadyExistsException("Username already exists");
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user).getId();
		}

	}

	@Override
	public UserDTO userToDto(User user) {
		UserDTO udto = new UserDTO();
		udto.setId(user.getId());
		udto.setMail(user.getMail());
		udto.setPassword(user.getPassword());
		udto.setRoleId(user.getRole().getId());
		udto.setUsername(user.getUsername());
		return udto;
	}

	@Override
	public User dtoToUser(UserDTO udto) {
		User u = new User();
		u.setId(udto.getId());
		u.setMail(udto.getMail());
		u.setUsername(udto.getUsername());
		u.setPassword(udto.getPassword());
		Optional<Role> r = roleRepository.findById(udto.getRoleId());
		if (r.isPresent())
			u.setRole(r.get());
		else
			u.setRole(roleRepository.findById(2L).get());
		return u;
	}
}
