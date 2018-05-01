package com.greenfoxacademy.reddit.models;

import javax.persistence.*;

@Entity
public class Vote {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id")
  private RedditUser user;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "post_id")
  private Post post;
  private int vote;

  public Vote(RedditUser user, Post post, int vote) {
    this.user = user;
    this.post = post;
    this.vote = vote;
  }

  public Vote() {
    //default constructor
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public RedditUser getUser() {
    return user;
  }

  public void setUser(RedditUser user) {
    this.user = user;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public int getVote() {
    return vote;
  }

  public void setVote(int vote) {
    this.vote = vote;
  }

}
