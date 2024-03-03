package ru.kolivim.myproject.task.management.system.api.resource.auth;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.myproject.task.management.system.api.dto.auth.*;

@Controller
@RequestMapping("api/v1/auth/")
@Tag(name = "Api сервиса аутентификации",
        description = "Сервис для регистрации, логина и их окружения")
public interface AuthController {

    @Operation(summary = "Аутентификация",
            description = "Вход в систему и последующее получение активного access токена")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Аутентификация прошла упешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticateResponseDto.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Аутентификация не удалась. Не верный логин или пароль",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticateResponseDto> login(@RequestBody AuthenticateDto authenticateDto);


    @Operation(summary = "Регистрация",
            description = "Регистрация нового аккаунта пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Регистрация прошла упешно"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Регистрация не удалась. Существует пользователь с таким email",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationDto loginDto);

}
