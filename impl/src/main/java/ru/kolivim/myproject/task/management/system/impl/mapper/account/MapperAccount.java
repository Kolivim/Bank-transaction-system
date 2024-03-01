package ru.kolivim.myproject.task.management.system.impl.mapper.account;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class MapperAccount {

    public Account toAccount(RegistrationDto registrationDto) {
        log.info("MapperAccount:toAccount(RegistrationDto registrationDto) startMethod, RegistrationDto: {}",
                registrationDto);
        Account account = new Account();
        account.setIsDeleted(false);
        account.setBalance(registrationDto.getBalance());
        account.setLogin(registrationDto.getLogin());
        account.setPassword(registrationDto.getPassword());
//        ArrayList<String> phoneList = new ArrayList<>();
//        phoneList.add(registrationDto.getPhone());
//        account.setPhoneList(phoneList);
//        ArrayList<String> emailList = new ArrayList<>();
//        emailList.add(registrationDto.getEmail());
//        account.setEmailList(emailList);
        log.info("MapperAccount:toAccount(RegistrationDto registrationDto) endMethod, Account: {}", account);
        return account;
    }

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
