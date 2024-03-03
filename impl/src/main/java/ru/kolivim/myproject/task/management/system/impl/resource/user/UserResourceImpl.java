package ru.kolivim.myproject.task.management.system.impl.resource.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDataDTO;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.api.resource.user.UserResource;
import ru.kolivim.myproject.task.management.system.impl.service.user.UserService;
import ru.kolivim.myproject.task.management.system.impl.utils.auth.AuthUtil;

import javax.security.auth.login.AccountException;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

    private final UserService userService;

    @Override
    public ResponseEntity addPhone(@RequestBody UserDataDTO userDataDTO) {
        userService.addPhone(userDataDTO);
        return ResponseEntity.ok("Номер добавлен");
    }

    @Override
    public ResponseEntity  updatePhone(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.updatePhone(userDataDTO));
    }

    @Override
    public ResponseEntity deletePhone(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.deletePhone(userDataDTO));
    }

    @Override
    public ResponseEntity addEmail(@RequestBody UserDataDTO userDataDTO) {
        userService.addEmail(userDataDTO);
        return ResponseEntity.ok("Email добавлен");
    }

    @Override
    public ResponseEntity  updateEmail(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.updateEmail(userDataDTO));
    }

    @Override
    public ResponseEntity deleteEmail(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.deleteEmail(userDataDTO));
    }

    @Override
    public ResponseEntity<Page<UserDto>> searchFullname(@RequestBody UserDto userDto, Pageable pageable) {
        return ResponseEntity.ok(userService.searchFullname(userDto, pageable));
    }

    @Override
    public ResponseEntity<Page<UserDto>> searchBirthDay(@RequestBody UserDto userDto, Pageable pageable) {
        return ResponseEntity.ok(userService.searchBirthDay(userDto, pageable));
    }

    @Override
    public ResponseEntity<UserDto> searchPhone(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.searchPhone(userDataDTO));
    }

    @Override
    public ResponseEntity<UserDto> searchEmail(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.searchEmail(userDataDTO));
    }

    @Override
    public ResponseEntity<UserDto> pay(@RequestBody UserDataDTO userDataDTO) {
        return ResponseEntity.ok(userService.pay(userDataDTO));
    }

}
