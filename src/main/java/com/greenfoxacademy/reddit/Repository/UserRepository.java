package com.greenfoxacademy.reddit.Repository;

import com.greenfoxacademy.reddit.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
