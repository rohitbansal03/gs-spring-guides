package com.example.spring.db.repository;

import com.example.spring.db.domain.Role;
import com.example.spring.enums.RoleType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT DISTINCT(r.name) FROM Role r")
    List<String> findDistinctRoles();

    Role findByName(RoleType name);

}
