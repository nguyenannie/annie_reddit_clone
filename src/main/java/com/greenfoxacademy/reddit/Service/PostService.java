package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;

@Service
public interface PostService {
    void save(Post post);
    List<Post> findAll();
    Post findOne(long id);
    Page<Post> findByPage(Pageable pageable);
    void delete(Post post);
}
