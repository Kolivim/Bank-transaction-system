package ru.kolivim.myproject.task.management.system.api.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;
import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;

import java.util.List;
import java.util.UUID;

@Data
public class TaskDTO  extends BaseDto {

    @Schema(description = "Статус задачи")
    private StatusType status;
    @Schema(description = "Приоритет задачи")
    private PriorityType priority;
    @Schema(description = "Заголовок задачи")
    private String title;
    @Schema(description = "Описание задачи")
    private String description;
    @Schema(description = "Идентификатор автора задачи")
    private UUID authorId;
    @Schema(description = "Идентификатор исполнителя задачи")
    private UUID executorId;
    @Schema(description = "Комментарии к задаче")
    private List<CommentDto> comments;

}
