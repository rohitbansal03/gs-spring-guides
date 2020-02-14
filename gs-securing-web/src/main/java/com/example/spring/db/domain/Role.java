package com.example.spring.db.domain;

import lombok.Data;
import lombok.ToString;

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

    @Column(unique = true)
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
