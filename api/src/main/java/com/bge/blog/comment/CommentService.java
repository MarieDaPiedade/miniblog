package com.bge.blog.comment;

import java.util.List;

import com.bge.blog.post.Post;

public interface CommentService {

	public Comment get(long id);
	
	public List<Comment> getAll();
	
	public List<Comment> getAll(Post p);
	
	public long save(Comment comment);
	
	public void update(long id, Comment comment);
	
	public void update(Comment comment);
	
	public void delete(long id);
	
	public CommentDTO commentToDto(Comment comment);

	public Comment dtoToComment(CommentDTO cdto);
}
