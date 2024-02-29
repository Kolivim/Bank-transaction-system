package ru.kolivim.myproject.task.management.system.impl.repository.task;

import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.task.Task;
import ru.kolivim.myproject.task.management.system.impl.repository.base.BaseRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends BaseRepository<Task> {
    Optional<Task> findByIdAndIsDeletedFalse(UUID id);

}
