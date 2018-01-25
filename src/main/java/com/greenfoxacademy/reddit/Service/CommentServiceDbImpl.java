package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.Model.Comment;
import com.greenfoxacademy.reddit.Model.Post;
import com.greenfoxacademy.reddit.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
