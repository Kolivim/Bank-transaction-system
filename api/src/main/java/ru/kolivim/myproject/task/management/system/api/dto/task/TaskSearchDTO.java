package ru.kolivim.myproject.task.management.system.api.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

import java.util.UUID;

@Data
public class TaskSearchDTO extends BaseDto {

    @Schema(description = "Идентификатор автора задачи")
    private UUID authorId;

}
