package com.greenfoxacademy.reddit.Repository;

import com.greenfoxacademy.reddit.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

