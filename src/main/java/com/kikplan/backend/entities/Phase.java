package com.kikplan.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="__phase")
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private String color;
    @OneToOne(mappedBy = "phase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Project project;
}
