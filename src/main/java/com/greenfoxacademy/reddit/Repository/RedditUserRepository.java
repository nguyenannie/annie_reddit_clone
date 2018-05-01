package com.greenfoxacademy.reddit.Repository;

import com.greenfoxacademy.reddit.models.RedditUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedditUserRepository extends CrudRepository<RedditUser, Long> {
    RedditUser findByName(String name);
}
