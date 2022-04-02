package com.bge.blog.post;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.bge.blog.user.User;

import lombok.Data;

@Entity
@Data
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name="created_at", nullable = false, columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
	@Generated(GenerationTime.INSERT)
	private Date createdAt;
	
	@Column(name = "is_moderated", nullable = false)
	private boolean isModerated;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
