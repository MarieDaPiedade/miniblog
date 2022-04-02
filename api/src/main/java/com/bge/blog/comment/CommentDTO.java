package com.bge.blog.comment;

import java.util.Date;


import lombok.Data;

@Data
public class CommentDTO {
	private long id;
	private long postId;
	private long userId;
	private boolean isModerated;
	private Date createdAt;
	private String content;
	private String author;
}
