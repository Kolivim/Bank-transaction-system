package ru.kolivim.myproject.task.management.system.impl.service.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.AuthenticateDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.AuthenticateResponseDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAccount;
import ru.kolivim.myproject.task.management.system.impl.service.user.UserService;
import ru.kolivim.myproject.task.management.system.impl.security.jwt.TokenGenerator;

import javax.security.auth.login.AccountException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userServices;
    private final MapperAccount mapperAccount;
    private final TokenGenerator tokenGenerator;

    public AuthenticateResponseDto login(AuthenticateDto authenticateDto) {
        JwtDto jwtDto = userServices.getJwtDto(authenticateDto);
        if(jwtDto==null){return new AuthenticateResponseDto();}
        AuthenticateResponseDto responseDto = new AuthenticateResponseDto();
        responseDto.setAccessToken(tokenGenerator.createToken(jwtDto));
        responseDto.setRefreshToken("Здесь будет рефреш токен");
        return responseDto;
    }

    public Boolean register(RegistrationDto registrationDto) {
        /*
        if(userServices.doesAccountWithSuchEmailExist(registrationDto.getEmail())){
            return false;
        }
        AccountDto accountDto = mapperAccount.accountDtoFromRegistrationDto(registrationDto);
        try {
            userServices.create(accountDto);
        } catch (AccountException e) {
            throw new RuntimeException(e);
        }
        */
        return true;
    }

}
