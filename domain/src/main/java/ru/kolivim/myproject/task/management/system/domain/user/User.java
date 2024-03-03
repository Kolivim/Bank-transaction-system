package ru.kolivim.myproject.task.management.system.domain.user;

import jakarta.persistence.*;
import lombok.*;
import ru.kolivim.myproject.task.management.system.domain.base.BaseEntity;

import java.time.ZonedDateTime;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    @Column(name="birth_date")
    ZonedDateTime birthDate;

    @Column(name = "full_name")
    private String fullname;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

}
