package com.greenfoxacademy.reddit.models.entities;

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
    private String creationDate;
    private String link;
    private String imageUrl;
    private String videoUrl;
    private String videoThumbnail;
    private int score;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "subReddit_id")
    private SubReddit subReddit;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private RedditUser user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Vote> votes;

    public Post() {
        creationDate = String.valueOf(LocalDate.now());
    }

    public Post(RedditUser author, String title, String content) {
        this.user = author;
        this.title = title;
        this.content = content;
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public SubReddit getSubReddit() {
        return subReddit;
    }

    public void setSubReddit(SubReddit subReddit) {
        this.subReddit = subReddit;
    }
}
