package ru.kolivim.myproject.task.management.system.impl.service.comment;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import ru.kolivim.myproject.task.management.system.api.dto.search.BaseSearchDto;
import ru.kolivim.myproject.task.management.system.impl.mapper.comment.CommentMapper;
import ru.kolivim.myproject.task.management.system.impl.mapper.task.TaskMapper;
import ru.kolivim.myproject.task.management.system.impl.repository.comment.CommentRepository;
import ru.kolivim.myproject.task.management.system.impl.service.task.TaskService;

@Slf4j
@ExtendWith({MockitoExtension.class})
@ContextConfiguration(classes = {

})
public class CommentServiceTest {
    @Mock
    private TaskService taskService;
    @Mock
    private CommentRepository commentRepository;
    private final CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
    private final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    @Test
    @DisplayName("Test")
    void getTest() {
        CommentService commentService = getNewCommentService();

        /*
        UUID authorId = UUID.randomUUID();
        UUID executorId = UUID.randomUUID();
        UUID authorId2 = UUID.randomUUID();
        UUID executorId2 = UUID.randomUUID();
        String title = "Тест заголовок";
        String description = "Тест описание";

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setAuthorId(authorId);
        taskDTO.setExecutorId(executorId);
        taskDTO.setPriority(PriorityType.LOW);
        taskDTO.setStatus(StatusType.COMPLETED);
        taskDTO.setDescription(description);
        taskDTO.setTitle(title);

        Task task = new Task();
        task.setIsDeleted(false);
        task.setAuthorId(authorId);
        task.setExecutorId(executorId);
        task.setPriority(PriorityType.HIGH);
        task.setStatus(StatusType.PENDING);
        task.setDescription(description);
        task.setTitle(title);

        Pageable page = PageRequest.of(0, 20, Sort.unsorted());

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(Comment_.AUTHOR_ID, authorId));

        when(commentRepository.findAll(taskSpecification, page)).thenReturn(settings);

        commentService.getAllByAuthorId(authorId, page);

        */

    }

    private CommentService getNewCommentService() {
        CommentService commentService = new CommentService(commentMapper, commentRepository, taskService, taskMapper);

        return commentService;
    }

    private BaseSearchDto getBaseSearchDto() {
        BaseSearchDto baseSearchDto = new BaseSearchDto();
        baseSearchDto.setIsDeleted(false);
        return baseSearchDto;
    }

}

/*
    public Page<CommentDto> getAllByAuthorId(UUID id, Pageable page) {
        log.info("CommentService: getAllByAuthorId(UUID id, Pageable page) startMethod, id: {}", id);
        Specification taskSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(Comment_.AUTHOR_ID, id));

        Page<Comment> comments = commentRepository.findAll(taskSpecification, page);
        Page<CommentDto> commentsDto = comments.map(commentMapper::modelToDto);
        return commentsDto;
    }
*/