package ru.kolivim.myproject.task.management.system.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
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
    ZonedDateTime birthday;

    @Schema(description = "ФИО пользователя")
    private String fullName;

    @Schema(description = "Логин")
    private String login;

    @Schema(description = "Пароль")
    private String password;

    /** Без него обойтись смогу*/
//    @Schema(description = "Идентификатор банковского аккаунта")
//    private UUID accountId;

// //////////////////////////////////////////////////////////////////////////////////////////

//    private LocalDateTime registrationDate;
//
//    private LocalDateTime createdOn;
//
//    private LocalDateTime updatedOn;

//    @Schema(description = "Фамилия пользователя")
//    private String lastName;
}
