package ru.kolivim.myproject.task.management.system.impl.resource.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kolivim.myproject.task.management.system.api.dto.auth.*;
import ru.kolivim.myproject.task.management.system.api.resource.auth.AuthController;
import ru.kolivim.myproject.task.management.system.impl.service.auth.AuthService;

@Slf4j
@Controller

@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;


    @Override
    public ResponseEntity<AuthenticateResponseDto> login(AuthenticateDto authenticateDto) {
        log.info("AuthControllerImpl: login(RegistrationDto registrationDto), registrationDto: {}",
                authenticateDto);

        AuthenticateResponseDto authenticateResponseDto = authService.login(authenticateDto);
        if(authenticateResponseDto.getAccessToken()==null){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(authenticateResponseDto);
    }

    @Override
    public ResponseEntity<String> register(RegistrationDto registrationDto) {
        log.info("AuthControllerImpl: register(RegistrationDto registrationDto), registrationDto: {}",
                registrationDto);

        if(!authService.register(registrationDto)){
            return ResponseEntity.badRequest().body("base has some user data");
        }

        log.info("AuthControllerImpl: register(RegistrationDto registrationDto) endMethod : registered");
        return ResponseEntity.ok("registered");
    }

}
