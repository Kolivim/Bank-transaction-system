package ru.kolivim.myproject.task.management.system.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.NotAudited;
import ru.kolivim.myproject.task.management.system.domain.base.BaseEntity;
import ru.kolivim.myproject.task.management.system.domain.base.audit.BaseAuditedEntity;
import ru.kolivim.myproject.task.management.system.domain.role.Role;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity /*BaseAuditedEntity*/ {

    /** Достаем селектом из таблицы Phone*/
    /*
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "phone", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "phone", nullable = false)
    private List<String> phoneList;
    */

    /** Достаем селектом из таблицы Email*/
    /*
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "email", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "email", nullable = false)
    private List<String> emailList;
    */

    @Column(name="birth_date")
    ZonedDateTime birthDate;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @NotAudited
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    /*
    @NotNull
    @Email
    @Size(max = 100)
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

    */

}
