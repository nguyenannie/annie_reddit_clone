package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.models.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    void save(Role role);
}
