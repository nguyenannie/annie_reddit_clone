package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.RedditUser;
import com.greenfoxacademy.reddit.models.Vote;
import com.greenfoxacademy.reddit.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceDbImpl implements VoteService {

  private final VoteRepository voteRepository;

  @Autowired
  public VoteServiceDbImpl(VoteRepository voteRepository) {
    this.voteRepository = voteRepository;
  }

  @Override
  public Vote findByPostAndUser(Post post, RedditUser user) {
    return voteRepository.findByPostAndUser(post, user);
  }

  @Override
  public List<Vote> findByPost(Post post) {
    return voteRepository.findByPost(post);
  }

  @Override
  public void save(Vote vote) {
    voteRepository.save(vote);
  }

  @Override
  public void delete(Vote vote) {
    voteRepository.delete(vote.getId());
  }

}
