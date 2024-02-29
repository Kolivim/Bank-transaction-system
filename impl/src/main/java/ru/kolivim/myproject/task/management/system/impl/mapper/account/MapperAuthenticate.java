package ru.kolivim.myproject.task.management.system.impl.mapper.account;

import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.domain.user.User;

public interface MapperAuthenticate {
    UserDto toDto(User user);
}
