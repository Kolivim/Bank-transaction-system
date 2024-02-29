package ru.kolivim.myproject.task.management.system.api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto extends BaseDto {

    @Schema(description = "Имя пользователя")
    private String firstName;
    @Schema(description = "Фамилия пользователя")
    private String lastName;
    @Schema(description = "Email пользователя")
    private String email;
    @Schema(description = "Пароль")
    private String password1;
    @Schema(description = "Повторение пароля")
    private String password2;

    private String captchaCode;

    private String captchaSecret;
}
