package ru.kolivim.myproject.task.management.system.api.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

import java.util.UUID;

@Data
public class CommentDto extends BaseDto {

    @Schema(description = "Идентификатор автора комментария")
    private UUID authorId;
    @Schema(description = "Текст  комментария")
    private String commentText;
    @Schema(description = "Идентификатор задачи")
    private UUID taskId;

}