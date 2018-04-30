package com.greenfoxacademy.reddit.Repository;

import com.greenfoxacademy.reddit.Model.Post;
import com.greenfoxacademy.reddit.Model.RedditUser;
import com.greenfoxacademy.reddit.Model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
  Vote findByPostAndUser(Post post, RedditUser user);
}
