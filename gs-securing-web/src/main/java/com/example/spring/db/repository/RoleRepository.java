package com.example.spring.db.repository;

import com.example.spring.db.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    public Role findByName(String name);

}
