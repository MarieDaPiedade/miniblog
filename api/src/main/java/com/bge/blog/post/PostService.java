package com.bge.blog.post;

import java.util.List;

public interface PostService {

	public Post get(long id);
	
	public List<Post> getAll(String username);
	
	public long save(Post post);
	
	public void update(long id, Post post);
	
	public void update(Post post);
	
	public void delete(long id);
	
	public PostDTO postToDto(Post post);

	public Post dtoToPost(PostDTO post);
	
}
