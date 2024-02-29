package ru.kolivim.myproject.task.management.system.impl.mapper.comment;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;
import ru.kolivim.myproject.task.management.system.domain.comment.Comment;
import ru.kolivim.myproject.task.management.system.impl.mapper.base.BaseMapper;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CommentMapper extends BaseMapper {

    @Mappings({
            @Mapping(target = "taskId", source = "task.id")})
    CommentDto modelToDto(Comment comment);

    @Mappings({
            @Mapping(target = "isDeleted", source = "isDeleted", defaultValue = "false")})
    Comment dtoToModel(CommentDto commentDto);

    Comment dtoToModel(CommentDto commentDto, @MappingTarget Comment comment);

}
