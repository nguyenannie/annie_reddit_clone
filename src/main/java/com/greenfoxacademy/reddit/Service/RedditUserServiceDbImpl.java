package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.Comment;
import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.RedditUser;
import com.greenfoxacademy.reddit.Repository.RedditUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedditUserServiceDbImpl implements RedditUserService {
    private final RedditUserRepository userRepository;


    @Autowired
    public RedditUserServiceDbImpl(RedditUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(RedditUser user) {
        userRepository.save(user);
    }

    @Override
    public RedditUser findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public boolean exists(long id) {
        return userRepository.exists(id);
    }

    @Override
    public boolean exists(String username) {
        return userRepository.findByName(username) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RedditUser user = findByName(username);
        if(user == null) {
            throw new UsernameNotFoundException("RedditUser not found");
        }
        return user;
    }
}