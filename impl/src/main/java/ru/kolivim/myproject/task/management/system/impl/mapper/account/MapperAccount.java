package ru.kolivim.myproject.task.management.system.impl.mapper.account;

import lombok.extern.log4j.Log4j;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;



@Log4j
@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class MapperAccount {
/*
    public abstract AccountDto toDto(Account account);
    public abstract Account toEntity(AccountDto accountDto);
    public abstract Account rewriteEntity(@MappingTarget Account account, AccountDto accountDto);
*/

    /*
    @Mapping(target = "password", source = "dto.password1")
    public abstract AccountDto accountDtoFromRegistrationDto(RegistrationDto dto);
    */
}
