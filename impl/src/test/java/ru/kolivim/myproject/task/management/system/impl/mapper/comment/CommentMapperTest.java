package ru.kolivim.myproject.task.management.system.impl.mapper.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;
import ru.kolivim.myproject.task.management.system.domain.comment.Comment;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentMapperTest {
    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @Test
    @DisplayName("Convert Comment Entity to DTO")
    void modelToDto() {

        UUID authorId = UUID.randomUUID();
        String commentText = "Тест коммент";

        Comment comment = new Comment();
        comment.setCommentText(commentText);
        comment.setAuthorId(authorId);

        CommentDto afterCommentDto = commentMapper.modelToDto(comment);

        assertThat(afterCommentDto).hasFieldOrPropertyWithValue("authorId", authorId);
        assertThat(afterCommentDto).hasFieldOrPropertyWithValue("commentText", commentText);

    }

    @Test
    @DisplayName("Convert Comment DTO to Entity")
    void dtoToModel() {

        UUID authorId = UUID.randomUUID();
        String commentText = "Тест коммент";

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(commentText);
        commentDto.setAuthorId(authorId);

        Comment afterComment = commentMapper.dtoToModel(commentDto);

        assertThat(afterComment).hasFieldOrPropertyWithValue("authorId", authorId);
        assertThat(afterComment).hasFieldOrPropertyWithValue("commentText", commentText);
        assertThat(afterComment).hasFieldOrPropertyWithValue("isDeleted", false);

    }

}
