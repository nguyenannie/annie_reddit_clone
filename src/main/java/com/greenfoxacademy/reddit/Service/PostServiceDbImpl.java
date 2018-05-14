package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceDbImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceDbImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        posts.addAll(postRepository.findAll());
        return posts;
    }

    @Override
    public Post findOne(long id) {
        return postRepository.findOne(id);
    }

    @Override
    public Page<Post> findByPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post.getId());
    }
}
