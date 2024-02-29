package ru.kolivim.myproject.task.management.system.api.resource.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;

import javax.security.auth.login.AccountException;
import java.util.UUID;

@RestController
//@RequestMapping("api/v1/account/")
@RequestMapping("api/v1/account")
@Tag(name = "Api сервиса аккаунта",
        description = "Сервис для создания, редактирования, получения и удаления аккаунта")
public interface AccountResource {

    @Operation(summary = "Получение данных акаунта по email",
            description = "Получение данных существующего акаунта по email")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Авторизация не пройдена. Необходимо авторизоваться",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к данным запрещен",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @GetMapping()
    public ResponseEntity get(
            @Parameter(description = "Email аккаунта") @RequestParam String email) throws AccountException;


    @Operation(summary = "Обновление данных аккаунта",
            description = "Обновление данных существующего аккаунта ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Авторизация не пройдена. Необходимо авторизоваться",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к данным запрещен",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PutMapping()
    public ResponseEntity<AccountDto> update(@RequestBody AccountDto account) throws AccountException;


    @Operation(summary = "Создание акаунта",
            description = "Создание нового акаунта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Авторизация не пройдена. Необходимо авторизоваться",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к данным запрещен",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping()
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto account) throws AccountException;


    @Operation(summary = "Получение данных аккаунта",
            description = "Получение данных текущего авторизированного пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Авторизация не пройдена. Необходимо авторизоваться",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к данным запрещен",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @GetMapping("/me")
    public ResponseEntity getMe() throws AccountException;


    @Operation(summary = "Обновление данных аккаунта",
            description = "Обновление данных текущего авторизированного пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Авторизация не пройдена. Необходимо авторизоваться",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к данным запрещен",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PutMapping("/me")
    public ResponseEntity putMe(@RequestBody AccountDto accountDto) throws AccountException;


    @Operation(summary = "Удаление аккаунта",
            description = "Удаление аккаунта текущего авторизированного пользователя, ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен.",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Авторизация не пройдена. Необходимо авторизоваться",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к данным запрещен",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @DeleteMapping("/me")
    public ResponseEntity deleteMe() throws AccountException;


    @Operation(summary = "Получение данных аккаунта по ID",
            description = "Получение данных аккаунта пользователя по переданному ID (UUID)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Авторизация не пройдена. Необходимо авторизоваться",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к данным запрещен",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @GetMapping("/{id}")
    public ResponseEntity getId(
            @Parameter(description = "Идентификатор аккаунта") @PathVariable UUID id)throws AccountException;

    @Operation(summary = "Удаление аккаунта по ID",
            description = "Удаление аккаунта пользователя по переданному ID (UUID)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не верный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Авторизация не пройдена. Необходимо авторизоваться",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к данным запрещен",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteId(
            @Parameter(description = "Идентификатор аккаунта") @PathVariable UUID id)throws AccountException;

}


