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
//import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;
//import ru.kolivim.myproject.task.management.system.api.dto.task.PriorityType;
//import ru.kolivim.myproject.task.management.system.api.dto.task.StatusType;
//import ru.kolivim.myproject.task.management.system.domain.comment.Comment;
//import ru.kolivim.myproject.task.management.system.domain.task.Task;
//import ru.kolivim.myproject.task.management.system.impl.mapper.comment.CommentMapper;
//import ru.kolivim.myproject.task.management.system.impl.repository.comment.CommentRepository;
//import ru.kolivim.myproject.task.management.system.impl.repository.task.TaskRepository;
//import ru.kolivim.myproject.task.management.system.impl.service.comment.CommentService;
import ru.kolivim.myproject.task.management.system.impl.service.task.TaskService;
import ru.kolivim.myproject.task.management.system.impl.utils.technikalUser.TechnicalUserConfig;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
*/
public class CommentServiceTest {
/*
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

        technicalUser.executeByTechnicalUser(() -> 1);

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        String commentText = "Тест коммент";
        String title = "Тест заголовок";
        String description = "Тест описание";

        Comment comment = new Comment();
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(commentText);
        commentDto.setTaskId(afterSaveTask.getId());

        assertDoesNotThrow(() -> commentService.create(commentDto));

    }

    @Test
    @Transactional
    @DisplayName("Test create Comment with Exception")
    public void createException() {

        technicalUser.executeByTechnicalUser(() -> 1);

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        String commentText = "Тест коммент";
        String title = "Тест заголовок";
        String description = "Тест описание";

        Comment comment = new Comment();
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(commentText);
        commentDto.setTaskId(taskId);

        assertThrows(Exception.class, () -> commentService.create(commentDto));

    }


    @Test
    @Transactional
    @DisplayName("Test get Comments active user with Exception")
    public void getAllMeAuthorIdException() {

        technicalUser.executeByTechnicalUser(() -> 1);

        UUID authorId = UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        String commentText = "Тест коммент";
        String title = "Тест заголовок";
        String description = "Тест описание";

        Comment comment = new Comment();
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(commentText);
        commentDto.setTaskId(afterSaveTask.getId());

        Pageable page = PageRequest.of(0, 20, Sort.unsorted());

        assertDoesNotThrow(() -> commentService.getAllMeAuthorId(page));
//        assertThrows(Exception.class, () -> commentService.getAllMeAuthorId(page));
    }

    @Test
    @Transactional
    @DisplayName("Test get Comments active user")
    public void getAllMeAuthorId() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        String commentText = "Тест коммент";
        String title = "Тест заголовок";
        String description = "Тест описание";

        Comment comment = new Comment();
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Task afterSaveTask = taskRepository.save(task);

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(commentText);
        commentDto.setTaskId(afterSaveTask.getId());

        Pageable page = PageRequest.of(0, 20, Sort.unsorted());

        assertDoesNotThrow(() -> commentService.getAllMeAuthorId(page));

    }

    @Test
    @Transactional
    @DisplayName("Test get Comment")
    public void getById() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        String commentText = "Тест коммент";
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

        Task afterSaveTask = taskRepository.save(task);

        Comment comment = new Comment();
        comment.setIsDeleted(false);
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);
        comment.setTask(afterSaveTask);
        Comment afterSaveComment = commentRepository.save(comment);

        assertDoesNotThrow(() -> commentService.getById(afterSaveComment.getId()));
//        assertThrows(Exception.class, () -> commentService.getById(afterSaveComment.getId()));
    }

    @Test
    @Transactional
    @DisplayName("Test get Comment with Exception")
    public void getByIdException() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        String commentText = "Тест коммент";
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

        Task afterSaveTask = taskRepository.save(task);

        Comment comment = new Comment();
        comment.setIsDeleted(false);
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);
        comment.setTask(afterSaveTask);
        Comment afterSaveComment = commentRepository.save(comment);

//        assertDoesNotThrow(() -> commentService.getById(afterSaveTask.getId()));
        assertThrows(Exception.class, () -> commentService.getById(commentId));
    }

    @Test
    @Transactional
    @DisplayName("Test delete Comment")
    public void deleteById() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId =  UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        String commentText = "Тест коммент";
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

        Task afterSaveTask = taskRepository.save(task);

        Comment comment = new Comment();
        comment.setIsDeleted(false);
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);
        comment.setTask(afterSaveTask);
        Comment afterSaveComment = commentRepository.save(comment);

        assertDoesNotThrow(() -> commentService.deleteById(afterSaveComment.getId()));
    }


    @Test
    @Transactional
    @DisplayName("Test delete Comment with Exception - not Found")
    public void deleteByIdException() {

        technicalUser.executeByTestUser(() -> 1);
        UUID commentId = UUID.randomUUID();

        assertThrows(Exception.class, () -> commentService.deleteById(commentId));
    }

    @Test
    @Transactional
    @DisplayName("Test delete Comment with Exception - active user is not author")
    public void deleteByIdExceptionAuthor() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId =  UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        String commentText = "Тест коммент";
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

        Task afterSaveTask = taskRepository.save(task);

        Comment comment = new Comment();
        comment.setIsDeleted(false);
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);
        comment.setTask(afterSaveTask);
        Comment afterSaveComment = commentRepository.save(comment);

        assertThrows(Exception.class, () -> commentService.deleteById(afterSaveComment.getId()));
    }

    @Test
    @Transactional
    @DisplayName("Test delete Comment with Exception - isDeleted true")
    public void deleteByIdExceptionIsDelete() {

        technicalUser.executeByTestUser(() -> 1);

        UUID authorId =  UUID.fromString("f7d1002-9cc6-11ee-8c90-0242ac120002");
        UUID executorId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        String commentText = "Тест коммент";
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

        Task afterSaveTask = taskRepository.save(task);

        Comment comment = new Comment();
        comment.setIsDeleted(true);
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);
        comment.setTask(afterSaveTask);
        Comment afterSaveComment = commentRepository.save(comment);

        assertThrows(Exception.class, () -> commentService.deleteById(afterSaveComment.getId()));
    }

//    @Test
//    @Transactional
//    void test() {
//        technicalUser.executeByTechnicalUser(() -> 2);
//        Assertions.assertEquals(1, 1);
//    }

*/
}
