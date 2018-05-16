package com.greenfoxacademy.reddit.Repository;

import com.greenfoxacademy.reddit.models.entities.Comment;
import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByPostAndUser(Post post, RedditUser user);
    List<Comment> findByPost(Post post);

}
