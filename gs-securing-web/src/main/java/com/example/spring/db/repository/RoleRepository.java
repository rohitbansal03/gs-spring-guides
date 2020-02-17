package com.example.spring.db.repository;

import com.example.spring.db.domain.Role;
import com.example.spring.enums.RoleType;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    public Role findByName(RoleType name);

}
