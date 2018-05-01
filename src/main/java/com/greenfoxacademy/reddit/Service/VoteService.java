package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.RedditUser;
import com.greenfoxacademy.reddit.models.Vote;
import org.springframework.stereotype.Service;

@Service
public interface VoteService {
  Vote findByPostAndUser(Post post, RedditUser user);
  void save(Vote vote);
  void delete(Vote vote);
}
