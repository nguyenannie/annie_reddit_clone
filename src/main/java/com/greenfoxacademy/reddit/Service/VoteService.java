package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.Model.Post;
import com.greenfoxacademy.reddit.Model.RedditUser;
import com.greenfoxacademy.reddit.Model.Vote;
import org.springframework.stereotype.Service;

@Service
public interface VoteService {
  Vote findByPostAndUser(Post post, RedditUser user);
  void save(Vote vote);
  void delete(Vote vote);
}
