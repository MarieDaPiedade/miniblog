package com.bge.blog.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bge.blog.comment.Comment;
import com.bge.blog.comment.CommentDTO;
import com.bge.blog.comment.CommentService;
import com.bge.blog.user.User;
import com.bge.blog.user.UserRepository;

@Service("postService")
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentService commentService;

	@Override
	public Post get(long id) {
		Optional<Post> postOption = postRepository.findById(id);
		if (postOption.isEmpty()) {
			throw new NullPointerException();
		} else {
			return postOption.get();
		}
	}

	public List<Post> getAll(String username) {
		Optional<User> userOptional = userRepository.findByUsername(username);
		long id;
		if (userOptional.isPresent()) {
			id = userOptional.get().getRole().getId();
		} else {
			id = -1;
		}
		return (List<Post>) (id == 1 ? postRepository.findAll() : postRepository.findAllByIsModerated(false));
	}

	@Override
	public long save(Post post) {
		return postRepository.save(post).getId();
	}

	@Override
	public void delete(long id) {
		postRepository.deleteById(id);
	}

	@Override
	public void update(long id, Post post) {
		post.setId(id);
		update(post);
	}
	
	@Override
	public void update(Post post) {
		postRepository.save(post);
	}

	@Override
	public PostDTO postToDto(Post post) {
		PostDTO pdto = new PostDTO();
		pdto.setContent(post.getContent());
		pdto.setTitle(post.getTitle());
		pdto.setId(post.getId());
		pdto.setCreatedAt(post.getCreatedAt());
		pdto.setUserId(post.getUser().getId());
		pdto.setAuthor(post.getUser().getUsername());
		pdto.setModerated(post.isModerated());
		List<Comment> lcomments= commentService.getAll(post);
		List<CommentDTO> lcdto = new ArrayList<>();
		for(Comment c : lcomments) {
			lcdto.add(commentService.commentToDto(c));
		}
		pdto.setComments(lcdto);
		return pdto;
	}


	@Override
	public Post dtoToPost(PostDTO pdto) {
		Post p = new Post();
		p.setId(pdto.getId());
		p.setContent(pdto.getContent());
		p.setTitle(pdto.getTitle());
		p.setCreatedAt(pdto.getCreatedAt());
		p.setModerated(pdto.isModerated());
		Optional<User> u = userRepository.findById(pdto.getUserId());
		if(u.isPresent())
			p.setUser(u.get());
		else
			p.setUser(null);
		return p;
	}
}
