package com.greenfoxacademy.reddit.models.forms;

public class PostForm {

  private String title;
  private String content;

  public PostForm(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public PostForm() {
    //default constructor
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
