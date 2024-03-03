package ru.kolivim.myproject.task.management.system.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto extends BaseDto {

    @Schema(description = "Идентификатор пользователя")
    private UUID id;

    @Schema(description = "Метка удаления")
    private boolean isDeleted;

    @Schema(description = "Номера телефонов / телефона")
    private List<String> phoneList;

    @Schema(description = "Список Email пользователя")
    private List<String> emailList;

    @Schema(description = "Дата рождения пользователя")
    ZonedDateTime birthDate;

    @Schema(description = "ФИО пользователя")
    private String fullname;

    @Schema(description = "Логин")
    private String login;

    @Schema(description = "Пароль")
    private String password;

}
