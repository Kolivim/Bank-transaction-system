package ru.kolivim.myproject.task.management.system.domain.account;

import jakarta.persistence.*;
import lombok.*;
import ru.kolivim.myproject.task.management.system.domain.user.User;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account extends User {

    @Column(name="balance")
    private Double balance;

    @Column(name="max_balance")
    private Double maxBalance;

}
