package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.Model.Role;
import com.greenfoxacademy.reddit.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceDbImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceDbImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
