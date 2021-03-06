package com.example.spring.db.domain;

import com.example.spring.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Roles for a user
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends Persistent {

    @Column(unique = true)
    @NonNull
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
