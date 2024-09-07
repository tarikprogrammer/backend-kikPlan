package com.kikplan.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="__user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    private boolean accountLocked;
    private boolean enabled;
    private String photo;
    private String colorProfile;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "user")
    private Role roles;

    @OneToOne(mappedBy = "user")
    private Subscription subscription;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="__team_User",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="team_id"))
    @JsonIgnore
    private List<Team> team;

    @Column(name="id_team",insertable = false,updatable = false)
    private Long teamId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Calendar>calendars;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Notification> notification;

    @OneToOne(mappedBy = "user")
    private TaskSettings taskSettings;

    @OneToMany(mappedBy = "user")
    private List<Session>sessions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<FeedBack>feedBacks;

    /*@Override
    public String getName() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.getName()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled(){
        return this.enabled;
    }*/
}
