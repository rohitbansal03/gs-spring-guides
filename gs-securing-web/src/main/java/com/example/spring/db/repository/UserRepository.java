package com.example.spring.db.repository;

import com.example.spring.db.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(long id);

    User findByEmail(String email);

}
