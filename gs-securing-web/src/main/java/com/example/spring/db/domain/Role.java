package com.example.spring.db.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Roles for a user
 */
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
