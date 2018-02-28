package com.greenfoxacademy.reddit.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String title;
    private String content;
    private int score;
    private String creationDate;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private RedditUser user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments;

    public Post() {
        creationDate = String.valueOf(LocalDate.now());
        score = 0;
    }

    public Post(RedditUser author, String title, String content, int score) {
        this.user = author;
        this.title = title;
        this.content = content;
        this.score = score;
        creationDate = String.valueOf(LocalDate.now());
    }

    public void setUser(RedditUser user) {
        setUser(user, true);
    }

    void setUser(RedditUser user, boolean add) {
        this.user = user;
        if (user != null && add) {
            user.addPost(this, false);
        }
    }

    public void addComment(Comment comment) {
        addComment(comment, true);
    }

    void addComment(Comment comment, boolean set) {
        if (comment!= null) {
            if(getComments().contains(comment)) {
                getComments().set(getComments().indexOf(comment),comment);
            }
            else {
                getComments().add(comment);
            }
            if (set) {
                comment.setPost(this, false);
            }
        }
    }

    public void removeComment(Comment comment) {
        getComments().remove(comment);
        comment.setPost(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public RedditUser getUser() {
        return user;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if ((object == null) || !(object instanceof Post)) {
            return false;
        }

        final Post post = (Post) object;

        return this.id != 0 && post.getId() != 0 && this.id == post.getId();
    }
}
