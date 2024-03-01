package ru.kolivim.myproject.task.management.system.impl.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.user.Email;
import ru.kolivim.myproject.task.management.system.impl.repository.base.BaseRepository;

import java.util.UUID;

@Repository
public interface EmailRepository extends BaseRepository<Email> /*JpaRepository<String Email, UUID> */ {
    int countByEmail(String email);
}
