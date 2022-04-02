package com.bge.blog.comment;

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

import com.bge.blog.post.Post;
import com.bge.blog.user.User;

import lombok.Data;

@Entity
@Data
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name="created_at", nullable = false, columnDefinition="DATETIME default CURRENT_TIMESTAMP")
	@Generated(GenerationTime.INSERT)
	private Date createdAt;
	
	@Column(name = "is_moderated", nullable = false)
	private boolean isModerated;
	
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
