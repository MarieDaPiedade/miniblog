package com.bge.blog.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bge.blog.post.Post;
import com.bge.blog.post.PostRepository;
import com.bge.blog.user.User;
import com.bge.blog.user.UserRepository;
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public Comment get(long id) {
		Optional<Comment> commentOption = commentRepository.findById(id);
		if (commentOption.isEmpty()) {
			throw new NullPointerException();
		} else {
			return commentOption.get();
		}
	}

	@Override
	public List<Comment> getAll() {
		return (List<Comment>) commentRepository.findAll();
	}

	@Override
	public List<Comment> getAll(Post p) {
		return commentRepository.findAllByPost(p);
	}

	@Override
	public long save(Comment comment) {
		return commentRepository.save(comment).getId();
	}

	@Override
	public void delete(long id) {
		commentRepository.deleteById(id);
		
	}

	@Override
	public void update(long id, Comment comment) {
		comment.setId(id);
		update(comment);
		
	}

	@Override
	public void update(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public CommentDTO commentToDto(Comment comment) {
		CommentDTO cdto = new CommentDTO();
		cdto.setPostId(comment.getPost().getId());
		cdto.setContent(comment.getContent());
		cdto.setId(comment.getId());
		cdto.setUserId(comment.getUser().getId());
		cdto.setAuthor(comment.getUser().getUsername());
		cdto.setModerated(comment.isModerated());
		cdto.setCreatedAt(comment.getCreatedAt());

		return cdto;
	}

	@Override
	public Comment dtoToComment(CommentDTO cdto) {
		Comment c = new Comment();
		c.setId(cdto.getId());
		c.setContent(cdto.getContent());
		c.setCreatedAt(cdto.getCreatedAt());
		c.setModerated(cdto.isModerated());
		Optional<User> u = userRepository.findById(cdto.getUserId());
		if(u.isPresent())
			c.setUser(u.get());
		else
			c.setUser(null);
		Optional<Post> p = postRepository.findById(cdto.getPostId());
		if(p.isPresent())
			c.setPost(p.get());
		else
			c.setPost(null);
		return c;
	}

}
