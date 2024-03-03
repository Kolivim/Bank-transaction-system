package ru.kolivim.myproject.task.management.system.impl.mapper.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;
import ru.kolivim.myproject.task.management.system.api.dto.task.PriorityType;
import ru.kolivim.myproject.task.management.system.api.dto.task.StatusType;
import ru.kolivim.myproject.task.management.system.api.dto.task.TaskDTO;
import ru.kolivim.myproject.task.management.system.domain.task.Task;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskMapperTest {
    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    @Test
    @DisplayName("Convert Task Entity to DTO")
    void modelToDto() {

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        String title = "Тест заголовок";
        String description = "Тест описание";

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        TaskDTO afterTaskDTO = taskMapper.toTaskDTO(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setIsDeleted(false);
        taskDTO.setAuthorId(authorId);
        taskDTO.setExecutorId(executorId);
        taskDTO.setPriority(PriorityType.HIGH);
        taskDTO.setStatus(StatusType.PENDING);
        taskDTO.setDescription(description);
        taskDTO.setTitle(title);
        taskDTO.setComments(new ArrayList<CommentDto>());

        assertThat(afterTaskDTO).hasFieldOrPropertyWithValue("isDeleted", false);
        assertThat(afterTaskDTO).hasFieldOrPropertyWithValue("authorId", authorId);
        assertThat(afterTaskDTO).hasFieldOrPropertyWithValue("executorId", executorId);
        assertThat(afterTaskDTO).hasFieldOrPropertyWithValue("priority", PriorityType.HIGH);
        assertThat(afterTaskDTO).hasFieldOrPropertyWithValue("status", StatusType.PENDING);
        assertThat(afterTaskDTO).hasFieldOrPropertyWithValue("description", description);
        assertThat(afterTaskDTO).hasFieldOrPropertyWithValue("title", title);

        Assertions.assertEquals(taskDTO, afterTaskDTO);
    }

    @Test
    @DisplayName("Convert Task DTO to Entity")
    void dtoToModel() {

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        String title = "Тест заголовок";
        String description = "Тест описание";

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setAuthorId(authorId);
        taskDTO.setExecutorId(executorId);
        taskDTO.setPriority(PriorityType.HIGH);
        taskDTO.setDescription(description);
        taskDTO.setTitle(title);
        taskDTO.setComments(new ArrayList<CommentDto>());

        Task afterTask = taskMapper.toTask(taskDTO);

        assertThat(afterTask).hasFieldOrPropertyWithValue("isDeleted", false);
        assertThat(afterTask).hasFieldOrPropertyWithValue("authorId", authorId);
        assertThat(afterTask).hasFieldOrPropertyWithValue("executorId", executorId);
        assertThat(afterTask).hasFieldOrPropertyWithValue("priority", PriorityType.HIGH);
        assertThat(afterTask).hasFieldOrPropertyWithValue("status", StatusType.PENDING);
        assertThat(afterTask).hasFieldOrPropertyWithValue("description", description);
        assertThat(afterTask).hasFieldOrPropertyWithValue("title", title);

    }

    @Test
    @DisplayName("Update Entity")
    void updateModel() {

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        String title = "Тест заголовок";
        String description = "Тест описание";

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setPriority(PriorityType.LOW);
        taskDTO.setDescription(description + "2");

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterTask = taskMapper.toTask(taskDTO, task);

        assertThat(afterTask).hasFieldOrPropertyWithValue("isDeleted", false);
        assertThat(afterTask).hasFieldOrPropertyWithValue("authorId", authorId);
        assertThat(afterTask).hasFieldOrPropertyWithValue("executorId", executorId);
        assertThat(afterTask).hasFieldOrPropertyWithValue("priority", PriorityType.LOW);
        assertThat(afterTask).hasFieldOrPropertyWithValue("status", StatusType.PENDING);
        assertThat(afterTask).hasFieldOrPropertyWithValue("description", description + "2");
        assertThat(afterTask).hasFieldOrPropertyWithValue("title", title);

    }

    @Test
    @DisplayName("Update Entity status")
    void updateStatusModel() {

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID authorId2 = UUID.randomUUID();
        UUID executorId2 = UUID.randomUUID();
        String title = "Тест заголовок";
        String description = "Тест описание";

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setAuthorId(authorId2);
        taskDTO.setExecutorId(executorId2);
        taskDTO.setPriority(PriorityType.LOW);
        taskDTO.setStatus(StatusType.COMPLETED);
        taskDTO.setDescription(description + "2");
        taskDTO.setTitle(title + "2");

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterTask = taskMapper.toTaskStatus(taskDTO, task);

        assertThat(afterTask).hasFieldOrPropertyWithValue("isDeleted", false);
        assertThat(afterTask).hasFieldOrPropertyWithValue("authorId", authorId);
        assertThat(afterTask).hasFieldOrPropertyWithValue("executorId", executorId);
        assertThat(afterTask).hasFieldOrPropertyWithValue("priority", PriorityType.HIGH);
        assertThat(afterTask).hasFieldOrPropertyWithValue("status", StatusType.COMPLETED);
        assertThat(afterTask).hasFieldOrPropertyWithValue("description", description);
        assertThat(afterTask).hasFieldOrPropertyWithValue("title", title);

    }

    @Test
    @DisplayName("Update Entity executor")
    void updateExecutorModel() {

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID authorId2 = UUID.randomUUID();
        UUID executorId2 = UUID.randomUUID();
        String title = "Тест заголовок";
        String description = "Тест описание";

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setAuthorId(authorId2);
        taskDTO.setExecutorId(executorId2);
        taskDTO.setPriority(PriorityType.LOW);
        taskDTO.setStatus(StatusType.COMPLETED);
        taskDTO.setDescription(description + "2");
        taskDTO.setTitle(title + "2");

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterTask = taskMapper.toTaskExecutor(taskDTO, task);

        assertThat(afterTask).hasFieldOrPropertyWithValue("isDeleted", false);
        assertThat(afterTask).hasFieldOrPropertyWithValue("authorId", authorId);
        assertThat(afterTask).hasFieldOrPropertyWithValue("executorId", executorId2);
        assertThat(afterTask).hasFieldOrPropertyWithValue("priority", PriorityType.HIGH);
        assertThat(afterTask).hasFieldOrPropertyWithValue("status", StatusType.PENDING);
        assertThat(afterTask).hasFieldOrPropertyWithValue("description", description);
        assertThat(afterTask).hasFieldOrPropertyWithValue("title", title);

    }

}