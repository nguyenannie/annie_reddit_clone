package com.greenfoxacademy.reddit.models.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class RedditUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String password;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<SubReddit> subReddits;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Vote> votes;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;


    public RedditUser() {
        this.enabled = true;
    }

    public void addPost(Post post) {
        addPost(post, true);
    }

    void addPost(Post post, boolean set) {
        if (post != null) {
            if(getPosts().contains(post)) {
                getPosts().set(getPosts().indexOf(post),post);
            }
            else {
                getPosts().add(post);
            }
            if (set) {
                post.setUser(this, false);
            }
        }
    }

    public void removePost(Post post) {
        getPosts().remove(post);
        post.setUser(null);
    }

    public void addComment(Comment comment) {
        addComment(comment, true);
    }

    public void addComment(Comment comment, boolean set) {
        if (comment != null) {
            if(getComments().contains(comment)) {
                getComments().set(getComments().indexOf(comment), comment);
            }
            else {
                getComments().add(comment);
            }
            if (set) {
                comment.setUser(this, false);
            }
        }
    }

    public void removeComment(Comment comment) {
        getComments().remove(comment);
        comment.setUser(null);
    }

    public RedditUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.enabled = true;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof RedditUser))
            return false;

        final RedditUser user = (RedditUser) object;

        return this.id != 0 && user.getId() != 0 && id == user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role.getName());
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<SubReddit> getSubReddits() {
        return subReddits;
    }

    public void setSubReddits(List<SubReddit> subReddits) {
        this.subReddits = subReddits;
    }
}
