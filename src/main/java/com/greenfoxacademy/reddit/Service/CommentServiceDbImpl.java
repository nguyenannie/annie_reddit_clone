package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.entities.Comment;
import com.greenfoxacademy.reddit.Repository.CommentRepository;
import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceDbImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceDbImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment findOne(long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        commentRepository.delete(id);
    }

    @Override
    public List<Comment> findByPostAndUser(Post post, RedditUser user) {
        return commentRepository.findByPostAndUser(post, user);
    }

    @Override
    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }

}
