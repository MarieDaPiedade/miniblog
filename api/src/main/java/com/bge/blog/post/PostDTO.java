package com.bge.blog.post;

import java.util.Date;
import java.util.List;

import com.bge.blog.comment.CommentDTO;

import lombok.Data;

@Data
public class PostDTO {
	private long id;
	private String title;
	private String content;
	private Date createdAt;
	private boolean isModerated;
	private long userId;
	private String author;
	private List<CommentDTO> comments;
}
