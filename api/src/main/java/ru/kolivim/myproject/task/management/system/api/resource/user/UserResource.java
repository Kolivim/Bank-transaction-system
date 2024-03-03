package ru.kolivim.myproject.task.management.system.api.resource.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDataDTO;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import org.springframework.data.domain.Pageable;

import javax.security.auth.login.AccountException;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "Api сервиса аккаунта",
        description = "Сервис для создания, редактирования аккаунта и проведения платежей")
public interface UserResource {

    @Operation(summary = "Добавление номера телефона пользователем",
            description = "Отправка запроса на добавление номера телефона, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = void.class))
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
                    responseCode = "404",
                    description = "Переданный номер телефона уже имеется",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/phone")
    ResponseEntity addPhone(@RequestBody UserDataDTO userDataDTO) throws Throwable;

    @Operation(summary = "Замена номера телефона пользователем",
            description = "Отправка запроса на изменение своего номера телефона, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = void.class))
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
                    responseCode = "404",
                    description = "Переданный новый номер телефона уже имеется / не найден заменяемый номер телефона",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PutMapping("/phone")
    ResponseEntity updatePhone(@RequestBody UserDataDTO userDataDTO) throws Throwable;

    @Operation(summary = "Удаление номера телефона",
            description = "Отправка запроса на удаление своего номера телефона, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
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
                    responseCode = "404",
                    description = "Переданный неверный номер телефона",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @DeleteMapping("/phone")
    public ResponseEntity deletePhone(@RequestBody UserDataDTO userDataDTO);

    @Operation(summary = "Добавление email пользователем",
            description = "Отправка запроса на добавление email, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = void.class))
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
                    responseCode = "404",
                    description = "Переданный Email уже имеется",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/email")
    ResponseEntity addEmail(@RequestBody UserDataDTO userDataDTO) throws Throwable;

    @Operation(summary = "Замена email пользователем",
            description = "Отправка запроса на изменение своего email, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = void.class))
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
                    responseCode = "404",
                    description = "Переданный новый email уже имеется / не найден заменяемый email",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PutMapping("/email")
    ResponseEntity updateEmail(@RequestBody UserDataDTO userDataDTO) throws Throwable;

    @Operation(summary = "Удаление email",
            description = "Отправка запроса на удаление своего email, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
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
                    responseCode = "404",
                    description = "Переданный неверный email",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @DeleteMapping("/email")
    public ResponseEntity deleteEmail(@RequestBody UserDataDTO userDataDTO);

    @Operation(summary = "Поиск пользователей",
            description = "Отправка запроса на поиск пользователей по полю fullname, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
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
                    responseCode = "404",
                    description = "Переданный неверный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/search/fullname")
    public ResponseEntity<Page<UserDto>> searchFullname(@RequestBody UserDto userDto, Pageable pageable);

    @Operation(summary = "Поиск пользователей",
            description = "Отправка запроса на поиск пользователей  по полю birthDay, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
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
                    responseCode = "404",
                    description = "Переданный неверный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/search/birthday")
    public ResponseEntity<Page<UserDto>> searchBirthDay(@RequestBody UserDto userDto, Pageable pageable);

    @Operation(summary = "Поиск пользователей",
            description = "Отправка запроса на поиск пользователей по номеру телефона, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
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
                    responseCode = "404",
                    description = "Переданный неверный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/search/phone")
    public ResponseEntity<UserDto> searchPhone(@RequestBody UserDataDTO userDataDTO);

    @Operation(summary = "Поиск пользователей",
            description = "Отправка запроса на поиск пользователей по email, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
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
                    responseCode = "404",
                    description = "Переданный неверный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/search/email")
    public ResponseEntity<UserDto> searchEmail(@RequestBody UserDataDTO userDataDTO);

    @Operation(summary = "Перевод денег",
            description = "Отправка запроса на перевода денег со счета авторизованного пользователя, на счет " +
                    "пользователя с указанным логином / телефоном / email")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
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
                    responseCode = "404",
                    description = "Переданный неверный запрос",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("/pay")
    public ResponseEntity<UserDto> pay(@RequestBody UserDataDTO userDataDTO);

}


