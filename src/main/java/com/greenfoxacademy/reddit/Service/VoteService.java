package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import com.greenfoxacademy.reddit.models.entities.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {
  Vote findByPostAndUser(Post post, RedditUser user);
  List<Vote> findByPost(Post post);
  void save(Vote vote);
  void delete(Vote vote);
}
