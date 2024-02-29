package ru.kolivim.myproject.task.management.system.impl.resource.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;
import ru.kolivim.myproject.task.management.system.api.resource.comment.CommentResource;
import ru.kolivim.myproject.task.management.system.impl.service.comment.CommentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommentResourceImpl implements CommentResource {

    private final CommentService commentService;

    @Override
    public ResponseEntity<CommentDto> create(CommentDto commentDto) throws Throwable {
        return ResponseEntity.ok(commentService.create(commentDto));
    }

    @Override
    public ResponseEntity<Page<CommentDto>> getByMeAuthorId(Pageable page) {
        return ResponseEntity.ok(commentService.getAllMeAuthorId(page));
    }


    @Override
    public ResponseEntity<CommentDto> getById(UUID id) throws Throwable {
        return ResponseEntity.ok(commentService.getById(id));
    }

    @Override
    public ResponseEntity<Page<CommentDto>> getByAuthorId(UUID id, Pageable page) {
        return ResponseEntity.ok(commentService.getAllByAuthorId(id, page));
    }

    @Override
    public ResponseEntity deleteById(UUID id) throws Throwable {
        commentService.deleteById(id);
        return ResponseEntity.ok().body("Комментарий удален успешно");
    }
}
