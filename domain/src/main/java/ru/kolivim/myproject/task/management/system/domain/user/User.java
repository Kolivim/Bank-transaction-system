package ru.kolivim.myproject.task.management.system.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.NotAudited;
import ru.kolivim.myproject.task.management.system.domain.base.audit.BaseAuditedEntity;
import ru.kolivim.myproject.task.management.system.domain.role.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseAuditedEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private LocalDateTime registrationDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @NotAudited
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
