package com.bge.blog.user;

import com.bge.blog.exception.UserAlreadyExistsException;

public interface UserService {

	public User get(long id);
	
	public long save(User user) throws UserAlreadyExistsException ;
	
	public UserDTO userToDto(User user);

	public User dtoToUser(UserDTO udto);
	
}
