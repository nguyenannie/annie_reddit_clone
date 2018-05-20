package com.greenfoxacademy.reddit.Repository;

import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import com.greenfoxacademy.reddit.models.entities.SubReddit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubRedditRepository extends CrudRepository<SubReddit, Long> {

    List<SubReddit> findByUser(RedditUser user);
    List<SubReddit> findAll();
    List<SubReddit> findByNameLike(String name);
    SubReddit findByName(String name);

}
