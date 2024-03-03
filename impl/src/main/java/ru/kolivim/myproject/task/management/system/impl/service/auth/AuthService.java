package ru.kolivim.myproject.task.management.system.impl.service.auth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.AuthenticateDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.AuthenticateResponseDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.user.Email;
import ru.kolivim.myproject.task.management.system.domain.user.Phone;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAccount;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAuthenticate;
import ru.kolivim.myproject.task.management.system.impl.mapper.user.EmailMapper;
import ru.kolivim.myproject.task.management.system.impl.mapper.user.PhoneMapper;
import ru.kolivim.myproject.task.management.system.impl.repository.account.AccountRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.EmailRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.PhoneRepository;
import ru.kolivim.myproject.task.management.system.impl.service.account.AccountService;
import ru.kolivim.myproject.task.management.system.impl.service.user.UserService;
import ru.kolivim.myproject.task.management.system.impl.security.jwt.TokenGenerator;

import javax.security.auth.login.AccountException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userServices;
    private final AccountService accountService;

    private final AccountRepository accountRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;

    private final MapperAccount mapperAccount;
    private final PhoneMapper phoneMapper;
    private final EmailMapper emailMapper;

    private final TokenGenerator tokenGenerator;

    public AuthenticateResponseDto login(AuthenticateDto authenticateDto) {
        JwtDto jwtDto = userServices.getJwtDto(authenticateDto);
        if(jwtDto==null){return new AuthenticateResponseDto();}
        AuthenticateResponseDto responseDto = new AuthenticateResponseDto();
        responseDto.setAccessToken(tokenGenerator.createToken(jwtDto));
        responseDto.setRefreshToken("Зарезервированное поле для refresh токена");
        return responseDto;
    }

    public Boolean register(RegistrationDto registrationDto) {
        log.info("AuthService: register(RegistrationDto registrationDto), registrationDto: {}",
                registrationDto);

        if(userServices.doesUserDataExist(registrationDto)){
            log.info("AuthService: register(*), user data is available in the database, registrationDto: {}",
                    registrationDto);
            return false;
        }

        return accountService.create(registrationDto);

    }

}
