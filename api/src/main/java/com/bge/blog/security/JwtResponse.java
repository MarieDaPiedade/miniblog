package com.bge.blog.security;

import java.io.Serializable;

import com.bge.blog.user.User;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String username;
	private final long id;
	private final long roleId;
	

	public JwtResponse(String jwttoken, User user) {
		this.jwttoken = jwttoken;
		this.username = user.getUsername();
		this.id = user.getId();
		this.roleId = user.getRole().getId();
	}

	public String getToken() {
		return this.jwttoken;
	}
	public String getUsername() {
		return this.username;
	}
	public long getId() {
		return this.id;
		
	}
	public long getRoleId() {
		return this.roleId;
	}
}