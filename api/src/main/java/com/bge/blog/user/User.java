package com.bge.blog.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.bge.blog.role.Role;

import lombok.Data;

@Entity
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username", nullable = false, unique = true)
	@NotNull
	@NotEmpty
	private String username;

	@Column(name = "mail", nullable = false, unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-][.][a-z]{2,}+$", message = "Merci de saisir une adresse email valide")
	@NotNull
	@NotEmpty
	private String mail;

	@Column(name = "password", nullable = false)
	@NotNull
	@NotEmpty
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	@NotNull
	private Role role;

}
