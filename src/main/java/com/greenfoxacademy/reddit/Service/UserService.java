package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);
    User findByName(String name);
    boolean exists(long id);
    boolean exists(String username);
}
