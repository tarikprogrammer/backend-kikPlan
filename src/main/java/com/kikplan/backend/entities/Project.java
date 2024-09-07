package com.kikplan.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kikplan.backend.abstractClass.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Table(name="__Project")
public class Project extends AbstractEntity {
    private String title;
    private String description;
    private String email;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_team")
    private Team team;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pahse_id")
    private Phase phase;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="project_id")
    private List<Task> tasks;
}
