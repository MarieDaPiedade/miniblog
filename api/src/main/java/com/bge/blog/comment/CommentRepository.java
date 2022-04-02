package com.bge.blog.comment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bge.blog.post.Post;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

	List<Comment> findAllByPost(Post p);

}
