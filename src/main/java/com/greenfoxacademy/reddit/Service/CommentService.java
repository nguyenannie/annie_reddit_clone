package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.entities.Comment;
import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void save(Comment comment);
    Comment findOne(long id);
    void delete(long id);
    List<Comment> findByPostAndUser(Post post, RedditUser user);
    List<Comment> findByPost(Post post);
}

