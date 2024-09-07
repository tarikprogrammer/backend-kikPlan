package com.kikplan.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="__team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int size;
    @ManyToMany(mappedBy = "team")
    private List<User> users;
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project>projects;
}
