package ru.kolivim.myproject.task.management.system.impl.resource.task;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.myproject.task.management.system.api.dto.task.TaskDTO;
import ru.kolivim.myproject.task.management.system.api.resource.task.TaskResource;
import ru.kolivim.myproject.task.management.system.impl.service.task.TaskService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaskResourceImpl implements TaskResource {

    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.create(taskDTO));
    }

    @Override
    public ResponseEntity update(TaskDTO taskDTO) throws Throwable {
        return ResponseEntity.ofNullable(taskService.update(taskDTO));
    }

    @Override
    public ResponseEntity updateStatus(TaskDTO taskDTO) throws Throwable {
        return ResponseEntity.ofNullable(taskService.updateStatus(taskDTO));
    }

    @Override
    public ResponseEntity updateExecutor(TaskDTO taskDTO) throws Throwable {
        return ResponseEntity.ofNullable(taskService.updateExecutor(taskDTO));
    }

    @Override
    public ResponseEntity<Page<TaskDTO>> getAllMeByFilter(TaskDTO taskDTO, Pageable page) {
        return ResponseEntity.ok(taskService.getAllMeByFilter(taskDTO, page));
    }

    @Override
    public ResponseEntity<Page<TaskDTO>> getAllByFilter(TaskDTO taskDTO, Pageable page) {
        return ResponseEntity.ok(taskService.getAllByFilter(taskDTO, page));
    }

    @Override
    public ResponseEntity<Page<TaskDTO>> getAllMeAuthorId(Pageable page) {
        return ResponseEntity.ok(taskService.getAllMeAuthorId(page));
    }

    @Override
    public ResponseEntity<Page<TaskDTO>> getAllMeExecutorId(Pageable page) {
        return ResponseEntity.ok(taskService.getAllMeExecutorId(page));
    }

    @Override
    public ResponseEntity<Page<TaskDTO>> getAllByAuthorId(UUID id, Pageable page) {
        return ResponseEntity.ok(taskService.getAllByAuthorId(id, page));
    }

    @Override
    public ResponseEntity<Page<TaskDTO>> getAllByExecutorId(UUID id, Pageable page) {
        return ResponseEntity.ok(taskService.getAllByExecutorId(id, page));
    }

    @Override
    public ResponseEntity deleteById(UUID id) throws Throwable {
        taskService.deleteById(id);
        return ResponseEntity.ok().body("Задача удалёна успешно");
    }

    @Override
    public ResponseEntity<TaskDTO> getById(UUID id) throws Throwable {
        return ResponseEntity.ok(taskService.getById(id));
    }

}
