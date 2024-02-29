package ru.kolivim.myproject.task.management.system.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.kolivim.myproject.task.management.system.api.dto.task.PriorityType;
import ru.kolivim.myproject.task.management.system.api.dto.task.StatusType;
import ru.kolivim.myproject.task.management.system.api.dto.task.TaskDTO;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.task.Task;
import ru.kolivim.myproject.task.management.system.impl.mapper.comment.CommentMapper;
import ru.kolivim.myproject.task.management.system.impl.mapper.task.TaskMapper;
import ru.kolivim.myproject.task.management.system.impl.repository.account.AccountRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.comment.CommentRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.task.TaskRepository;
import ru.kolivim.myproject.task.management.system.impl.service.comment.CommentService;
import ru.kolivim.myproject.task.management.system.impl.service.task.TaskService;
import ru.kolivim.myproject.task.management.system.impl.utils.technikalUser.TechnicalUserConfig;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class TaskServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommentService commentService;
    @Autowired
    TaskService taskService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    JwtEncoder jwtEncoder;
    @Autowired
    TechnicalUserConfig technicalUser;
    @Autowired
    JwtEncoder accessTokenEncoder;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    AccountRepository accountRepository;
//    @Autowired
//    UserRepository userRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withInitScript("ru/kolivim/myproject/task/management/system/impl/schema.sql");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgres::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgres::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @Test
    @Transactional
    @DisplayName("Test create Comment")
    public void create() {

        technicalUser.executeByTestUser(() -> 1);

//        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
//        UUID taskId = UUID.randomUUID();
//        String commentText = "Тест коммент";
        String title = "Тест заголовок";
        String description = "Тест описание";

//        Comment comment = new Comment();
//        comment.setCommentText(commentText);
//        comment.setAuthorId(authorId);

//        Task task = new Task();
//        task.setIsDeleted(false);
//        task.setAuthorId(authorId);
//        task.setExecutorId(executorId);
//        task.setPriority(PriorityType.HIGH);
//        task.setStatus(StatusType.PENDING);
//        task.setDescription(description);
//        task.setTitle(title);

//        Task afterSaveTask = taskRepository.save(task);

//        CommentDto commentDto = new CommentDto();
//        commentDto.setCommentText(commentText);
//        commentDto.setTaskId(afterSaveTask.getId());

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
//        executor.setRoles(roleService.getRoleSet(Arrays.asList("USER","MODERATOR")));
        Account executorAfterSave = accountRepository.save(executor);

        TaskDTO taskDTO = new TaskDTO();
//        taskDTO.setExecutorId(executorId);
        taskDTO.setExecutorId(executorAfterSave.getId());
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setPriority(PriorityType.LOW);

        assertDoesNotThrow(() -> taskService.create(taskDTO));

    }

    @Test
    @Transactional
    @DisplayName("Test create Comment with Exception - not found Executor")
    public void createExceptionExecutor() {

        technicalUser.executeByTestUser(() -> 1);

        UUID executorId = UUID.randomUUID();
        String title = "Тест заголовок";
        String description = "Тест описание";

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setExecutorId(executorId);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setPriority(PriorityType.LOW);

        assertThrows(Exception.class, () -> taskService.create(taskDTO));

    }

    @Test
    @Transactional
    @DisplayName("Test create Comment with Exception - Executor IsDeleted = true")
    public void createExceptionExecutorIsDeleted() {

        technicalUser.executeByTestUser(() -> 1);

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(true);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setExecutorId(executorAfterSave.getId());
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setPriority(PriorityType.LOW);

        assertThrows(Exception.class, () -> taskService.create(taskDTO));

    }

    @Test
    @Transactional
    @DisplayName("Test get all my task")
    public void getAllMeAuthorId() {

        technicalUser.executeByTestUser(() -> 1);

        //        UUID authorId = UUID.randomUUID();
        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");
//        UUID executorId = UUID.randomUUID();
        //        UUID taskId = UUID.randomUUID();
        //        String commentText = "Тест коммент";
        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        Pageable page = PageRequest.of(0, 20, Sort.unsorted());

//        TaskDTO taskDTO = new TaskDTO();
//        taskDTO.setExecutorId(executorAfterSave.getId());
//        taskDTO.setTitle(title);
//        taskDTO.setDescription(description);
//        taskDTO.setPriority(PriorityType.LOW);

        assertDoesNotThrow(() -> taskService.getAllMeAuthorId(page));

    }

//    getAllMeExecutorId
@Test
@Transactional
@DisplayName("Test get all tasks executor")
public void getAllMeExecutorId() {

    technicalUser.executeByTestUser(() -> 1);

    UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");
    UUID executorId = authorId;
    String title = "Тест заголовок";
    String description = "Тест описание";

//    Account executor = new Account();
//    executor.setIsDeleted(false);
//    executor.setEmail("test@executor.ru");
//    executor.setPassword("11112222");
//    Account executorAfterSave = accountRepository.save(executor);

    Task task = new Task();
    task.setIsDeleted(false);
    task.setAuthorId(authorId);
    task.setExecutorId(executorId);
    task.setPriority(PriorityType.HIGH);
    task.setStatus(StatusType.PENDING);
    task.setDescription(description);
    task.setTitle(title);

    Task afterSaveTask = taskRepository.save(task);

    Pageable page = PageRequest.of(0, 20, Sort.unsorted());

//        TaskDTO taskDTO = new TaskDTO();
//        taskDTO.setExecutorId(executorAfterSave.getId());
//        taskDTO.setTitle(title);
//        taskDTO.setDescription(description);
//        taskDTO.setPriority(PriorityType.LOW);

    assertDoesNotThrow(() -> taskService.getAllMeExecutorId(page));

}

    @Test
    @Transactional
    @DisplayName("Test get all task by filter")
    public void getAllMeByFilter() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        Pageable page = PageRequest.of(0, 20, Sort.unsorted());

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setExecutorId(executorAfterSave.getId());
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setPriority(PriorityType.LOW);

//        assertDoesNotThrow(() -> taskService.getAllByFilter(taskDTO, page));
        assertDoesNotThrow(() -> taskService.getAllMeByFilter(taskDTO, page));
    }


    @Test
    @Transactional
    @DisplayName("Test delete task")
    public void deleteById() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        assertDoesNotThrow(() -> taskService.deleteById(afterSaveTask.getId()));
    }

    @Test
    @Transactional
    @DisplayName("Test delete task with Exception - user is not author task")
    public void deleteByIdException() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(UUID.randomUUID());
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        assertThrows(Exception.class, () -> taskService.deleteById(afterSaveTask.getId()));
    }

    @Test
    @Transactional
    @DisplayName("Test update task")
    public void update() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(afterSaveTask.getId());
        taskDTO.setTitle(title + "2");
        taskDTO.setDescription(description + "2");

        assertDoesNotThrow(() -> taskService.update(taskDTO));
    }

    @Test
    @Transactional
    @DisplayName("Test update task with Exception - user is not author task")
    public void updateException() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(UUID.randomUUID());
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(afterSaveTask.getId());
        taskDTO.setTitle(title + "2");
        taskDTO.setDescription(description + "2");

        assertThrows(Exception.class, () -> taskService.update(taskDTO));
    }

    @Test
    @Transactional
    @DisplayName("Test update status task")
    public void updateStatus() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(afterSaveTask.getId());
        taskDTO.setStatus(StatusType.COMPLETED);

        assertDoesNotThrow(() -> taskService.updateStatus(taskDTO));
    }

    @Test
    @Transactional
    @DisplayName("Test update status task with Exception - user is not author/executor task")
    public void updateStatusException() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(UUID.randomUUID());
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(afterSaveTask.getId());
        taskDTO.setStatus(StatusType.COMPLETED);

        assertThrows(Exception.class, () -> taskService.updateStatus(taskDTO));
    }

    @Test
    @Transactional
    @DisplayName("Test update executor task")
    public void updateExecutor() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Account newExecutor = new Account();
        newExecutor.setIsDeleted(false);
        newExecutor.setEmail("test2@executor.ru");
        newExecutor.setPassword("11112222");
        Account newExecutorAfterSave = accountRepository.save(newExecutor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(afterSaveTask.getId());
        taskDTO.setExecutorId(newExecutorAfterSave.getId());

        assertDoesNotThrow(() -> taskService.updateExecutor(taskDTO));
    }

    @Test
    @Transactional
    @DisplayName("Test update executor task with Exception - user is not author task")
    public void updateExecutorException() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");

        String title = "Тест заголовок";
        String description = "Тест описание";

        Account executor = new Account();
        executor.setIsDeleted(false);
        executor.setEmail("test@executor.ru");
        executor.setPassword("11112222");
        Account executorAfterSave = accountRepository.save(executor);

        Account newExecutor = new Account();
        newExecutor.setIsDeleted(false);
        newExecutor.setEmail("test2@executor.ru");
        newExecutor.setPassword("11112222");
        Account newExecutorAfterSave = accountRepository.save(newExecutor);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(UUID.randomUUID());
        task.setExecutorId(executorAfterSave.getId());
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(afterSaveTask.getId());
        taskDTO.setExecutorId(newExecutorAfterSave.getId());

        assertThrows(Exception.class, () -> taskService.updateExecutor(taskDTO));
    }
}
