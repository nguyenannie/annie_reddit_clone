package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.Model.Post;
import com.greenfoxacademy.reddit.Model.User;
import com.greenfoxacademy.reddit.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDbImpl implements UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserServiceDbImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
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
}