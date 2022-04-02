package com.bge.blog;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//import com.bge.blog.comment.CommentRepository;
//import com.bge.blog.post.PostRepository;
import com.bge.blog.role.Role;
import com.bge.blog.role.RoleRepository;
//import com.bge.blog.user.UserRepository;

/* This component will be removed when the application is finished */

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

   // @Autowired
  //  private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;

 //   @Autowired
 //   private PostRepository postRepository;

 //   @Autowired
 //   private CommentRepository commentRepository;
    
 //   @Autowired
 //   private PasswordEncoder passwordEncoder;
 
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
  /*      if (alreadySetup)
            return;
        
        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("USER");
        
        User user = new User();
        user.setUsername("AdminTest");
        user.setPassword(passwordEncoder.encode("AdminTest"));
        user.setMail("admintest@admintest.org");
        user.setRole(roleRepository.findByName("ADMIN").get());
        userRepository.save(user);
        
        
        user = new User();
        user.setUsername("UserTest");
        user.setPassword(passwordEncoder.encode("UserTest"));
        user.setMail("usertest@usertest.org");
        user.setRole(roleRepository.findByName("USER").get());
        userRepository.save(user);

        Post p = new Post();
        p.setUser(userRepository.findByUsername("UserTest").get());
        p.setModerated(false);
        p.setTitle("Coucou");
        p.setContent("content");

        postRepository.save(p);

        p = new Post();
        p.setUser(userRepository.findByUsername("AdminTest").get());
        p.setModerated(false);
        p.setTitle("Coucou admin");
        p.setContent("content admin");

        postRepository.save(p);
        
        Comment c = new Comment();
        c.setUser(userRepository.findByUsername("AdminTest").get());
        c.setPost(postRepository.findByTitle("Coucou admin").get());
        c.setContent("Comment Admin");
        
        commentRepository.save(c);
        
        c = new Comment();
        c.setUser(userRepository.findByUsername("UserTest").get());
        c.setPost(postRepository.findByTitle("Coucou").get());
        c.setContent("Salut à toi Admin");
        
        commentRepository.save(c);
        c = new Comment();
        c.setUser(userRepository.findByUsername("UserTest").get());
        c.setPost(postRepository.findByTitle("Coucou admin").get());
        c.setContent("Salut à moi");
        
        commentRepository.save(c);
        alreadySetup = true;*/
    }

    @Transactional
    Role createRoleIfNotFound( String name) {
    	Role r;
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isEmpty()) {
            r = new Role();
            r.setName(name);
            roleRepository.save(r);
        }
        else {
        	r= role.get();
        }
        return r;
    }
}

