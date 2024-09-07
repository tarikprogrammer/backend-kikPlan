package com.kikplan.backend.abstractClass;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public  class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate()
    @Column(nullable = false,updatable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "dd,MMM yyyy" ,locale ="EN")
    private LocalDate createdAt;
    @LastModifiedDate
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "dd MMMM yyyy")
    @Column(insertable = false)
    private LocalDate updatedAt;
}
