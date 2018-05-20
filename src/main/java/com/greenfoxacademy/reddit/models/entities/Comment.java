package com.greenfoxacademy.reddit.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String creationDate;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private RedditUser user;

    public Comment() {
        creationDate = String.valueOf(LocalDateTime.now());
    }

    public Comment(RedditUser user, Post post, String content) {
        this.user = user;
        this.content = content;
        this.post = post;
        creationDate = String.valueOf(LocalDateTime.now());
    }

    public void setUser(RedditUser user) {
        setUser(user, true);
    }

    void setUser(RedditUser user, boolean add) {
        this.user = user;
        if (user != null && add) {
            user.addComment(this, false);
        }
    }

    public void setPost(Post post) {
        setPost(post, true);
    }

    void setPost(Post post, boolean add) {
        this.post = post;
        if (post != null && add) {
            post.addComment(this, false);
        }
    }

    public RedditUser getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if ((object == null) || !(object instanceof Comment)) {
            return false;
        }

        final Comment comment = (Comment) object;

        return this.id != 0 && comment.getId() != 0 && this.id == comment.getId();
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
