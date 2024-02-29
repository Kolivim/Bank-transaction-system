package ru.kolivim.myproject.task.management.system.api.resource.comment;

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
import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;

import java.util.UUID;

@RequestMapping("/api/v1/task/comment")
@Tag(name = "Api сервиса комментариев",
        description = "Сервис для создания, получения, удаления комментариев")
public interface CommentResource {

    @Operation(summary = "Получение комментариев",
            description = "Отправка запроса на получение всех комментариев авторизованного пользователя")
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
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @GetMapping("")
    ResponseEntity<Page<CommentDto>> getByMeAuthorId(Pageable page);


    @Operation(summary = "Получение комментария",
            description = "Отправка запроса на получениепо идентификатору комментария")
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
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Комментария с таким UUID не существует",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @GetMapping("/{id}")
    ResponseEntity<CommentDto> getById(
            @Parameter(description = "Идентификатор комментария") @PathVariable UUID id) throws Throwable;


    @Operation(summary = "Получение комментариев",
            description = "Отправка запроса на получение всех комментариев автора переданного идентификатора")
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
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @GetMapping("/author/{id}")
    ResponseEntity<Page<CommentDto>> getByAuthorId(
            @Parameter(description = "Идентификатор комментария") @PathVariable UUID id, Pageable page);


    @Operation(summary = "Создание комментария",
            description = "Отправка запроса на создание комментария текущим авторизованным пользователем")
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
                            @Content(schema = @Schema(implementation = void.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Задачи с таким UUID не существует",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @PostMapping("")
    ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) throws Throwable;


    @Operation(summary = "Удаление комментария",
            description = "Отправка запроса на удаление комментария текущим авторизованным пользователем")
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
                    description = "Комментария с таким UUID не существует либо вы не являетесь его автором",
                    content = {
                            @Content(schema = @Schema(implementation = void.class))
                    })
    })
    @DeleteMapping("/{id}")
    ResponseEntity deleteById(
            @Parameter(description = "Идентификатор комментария") @PathVariable UUID id) throws Throwable;

}
