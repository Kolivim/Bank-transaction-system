package ru.kolivim.myproject.task.management.system.impl.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.user.User;
import ru.kolivim.myproject.task.management.system.impl.repository.base.BaseRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User>  /*JpaRepository<User, UUID> */{
    Optional<User> findByIdAndIsDeletedFalse(UUID id);

//    Optional<User> findFirstByEmail(UUID id);

    Optional<User> findByLogin(String login);
    Optional<User> findByLoginAndIsDeletedFalse(String login);
}
