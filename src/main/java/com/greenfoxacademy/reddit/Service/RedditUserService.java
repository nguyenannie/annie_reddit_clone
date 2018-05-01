package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.RedditUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface RedditUserService extends UserDetailsService {
    void save(RedditUser user);
    RedditUser findByName(String name);
    boolean exists(long id);
    boolean exists(String username);
}
