package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import com.greenfoxacademy.reddit.models.entities.SubReddit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubRedditService {

    void save(SubReddit subReddit);
    void delete(SubReddit subReddit);
    SubReddit findOne(long id);
    List<SubReddit> findAll();
    List<SubReddit> findByUser(RedditUser user);
//    List<SubReddit> findByPost(Post post);
//    List<SubReddit> findByUserAndPost(RedditUser user, Post post);

}
