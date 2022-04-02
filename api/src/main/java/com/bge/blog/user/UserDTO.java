package com.bge.blog.user;

import lombok.Data;

@Data
public class UserDTO {
	private long id;
	private String username;
	private long roleId;
	private String mail;
	private String password;
}
