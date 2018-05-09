package com.greenfoxacademy.reddit.Repository;

import com.greenfoxacademy.reddit.models.Comment;
import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.RedditUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByPostAndUser(Post post, RedditUser user);

}
