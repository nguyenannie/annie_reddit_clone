package com.greenfoxacademy.reddit.Repository;

import com.greenfoxacademy.reddit.models.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
