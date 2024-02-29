package ru.kolivim.myproject.task.management.system.api.resource.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.myproject.task.management.system.api.dto.task.TaskDTO;
import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;

import java.util.UUID;

@RequestMapping("/api/v1/task")
@Tag(name = "Api сервиса задач",
        description = "Сервис для создания, получения, обновления и удаления задач")
public interface TaskResource {

    @Operation(summary = "Создание задачи",
            description = "Отправка запроса на создание задачи авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CommentDto.class))
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
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пользователя с таким UUID не существует",
                    content = {
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    })
    })
    @PostMapping("")
    ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO);


    @Operation(summary = "Обновление задачи",
            description = "Отправка запроса на обновление задачи авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CommentDto.class))
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
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задачи с таким UUID не существует либо вы не являетесь автором задачи",
                    content = {
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    })
    })
    @PutMapping("")
    ResponseEntity update(@RequestBody TaskDTO taskDTO) throws Throwable;


    @Operation(summary = "Обновление статуса задачи",
            description = "Отправка запроса на обновление статуса задачи, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CommentDto.class))
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
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задачи с таким UUID не существует либо вы не являетесь " +
                            "автором/исполнителем задачи",
                    content = {
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    })
    })
    @PutMapping("/status")
    ResponseEntity updateStatus(@RequestBody TaskDTO taskDTO) throws Throwable;


    @Operation(summary = "Обновление исполнителя задачи",
            description = "Отправка запроса на обновление исполнителя задачи, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задачи с таким UUID не существует либо вы не являетесь автором задачи",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PutMapping("/executor")
    ResponseEntity updateExecutor(@RequestBody TaskDTO taskDTO) throws Throwable;


    @Operation(summary = "Получение задач",
            description = "Отправка запроса на получение задач авторизованного пользователя, " +
                    "с фильтрованием согласно переданным полям фильтра")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                    })
    })
    @GetMapping("/filter/me")
    ResponseEntity<Page<TaskDTO>> getAllMeByFilter(@RequestBody TaskDTO taskDTO, Pageable page);


    @Operation(summary = "Получение задач",
            description = "Отправка запроса на получение задач согласно переданного идентификатора пользователя, " +
                    "с фильтрованием согласно переданным полям фильтра")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                    })
    })
    @GetMapping("/filter")
    ResponseEntity<Page<TaskDTO>> getAllByFilter(@RequestBody TaskDTO taskDTO, Pageable page);


    @Operation(summary = "Получение задач",
            description = "Отправка запроса на получение всех задач авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                    })
    })
    @GetMapping("/author")
    ResponseEntity<Page<TaskDTO>> getAllMeAuthorId(Pageable page);


    @Operation(summary = "Получение задач",
            description = "Отправка запроса на получение всех задач исполнителя, согласно переданного идентификатора")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                    })
    })
    @GetMapping("/executor")
    ResponseEntity<Page<TaskDTO>> getAllMeExecutorId(Pageable page);


    @Operation(summary = "Получение задач",
            description = "Отправка запроса на получение всех задач авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                    })
    })
    @GetMapping("/author/{id}")
    ResponseEntity<Page<TaskDTO>> getAllByAuthorId(
            @Parameter(description = "Идентификатор автора") @PathVariable UUID id, Pageable page);


    @Operation(summary = "Получение задач",
            description = "Отправка запроса на получение всех задач исполнителя, согласно переданного идентификатора")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                    })
    })
    @GetMapping("/executor/{id}")
    ResponseEntity<Page<TaskDTO>> getAllByExecutorId(
            @Parameter(description = "Идентификатор исполнителя") @PathVariable UUID id, Pageable page);


    @Operation(summary = "Удаление задачи",
            description = "Отправка запроса на удаление задачи, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задачи с таким UUID не существует либо вы не являетесь автором задачи",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @DeleteMapping("/{id}")
    ResponseEntity deleteById(
            @Parameter(description = "Идентификатор задачи")  @PathVariable UUID id) throws Throwable;


    @Operation(summary = "Просмотр задачи",
            description = "Отправка запроса на просмотр задачи, авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class))
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
                            @Content(schema = @Schema(implementation = CommentDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задачи с таким UUID не существует",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @GetMapping("/{id}")
    ResponseEntity<TaskDTO> getById(
            @Parameter(description = "Идентификатор задачи") @PathVariable UUID id) throws Throwable;

}
