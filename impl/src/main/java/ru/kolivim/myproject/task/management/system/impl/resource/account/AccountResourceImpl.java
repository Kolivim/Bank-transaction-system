package ru.kolivim.myproject.task.management.system.impl.resource.account;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.api.resource.user.UserResource;
import ru.kolivim.myproject.task.management.system.impl.service.user.UserService;
import ru.kolivim.myproject.task.management.system.impl.utils.auth.AuthUtil;

import javax.security.auth.login.AccountException;
import java.util.UUID;

/**
 * Account
 *
 * @taras281 Taras
 */
@Slf4j
@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountResourceImpl implements UserResource {

    private final UserService userServices;

    @Override
    @GetMapping()
    public ResponseEntity get(@RequestParam String email) {
        log.info("AccountResourceImpl:get() startMethod");
        try {
            return ResponseEntity.ok(userServices.getByEmail(email));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    @PutMapping()
    public ResponseEntity<AccountDto> update(@RequestBody AccountDto account) {
        log.info("AccountResourceImpl:update() startMethod");
        try {
            return ResponseEntity.ok(userServices.update(account));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    @PostMapping()
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto account) {
        log.info("AccountResourceImpl:create() startMethod");
        try {
            return ResponseEntity.ok(userServices.create(account));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity getMe() {
        log.info("AccountResourceImpl:getMe() startMethod");
        try {
            return ResponseEntity.ok(userServices.getMe());
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    public ResponseEntity putMe(@RequestBody AccountDto accountDto) throws AccountException {
        log.info("AccountResourceImpl:putMe() startMethod");
        try {
            return ResponseEntity.ok(userServices.putMe(accountDto));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }
    @Override
    public ResponseEntity deleteMe() throws AccountException {
        log.info("AccountResourceImpl:deleteMe() startMethod");
        try {
            return ResponseEntity.ok(userServices.delete());
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    public ResponseEntity getId(@PathVariable UUID id) {
        log.info("AccountResourceImpl:getId() startMethod");
        try {
            return ResponseEntity.ok(userServices.getId(id));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    @Override
    public ResponseEntity deleteId(UUID id) throws AccountException {
        log.info("AccountResourceImpl:deleteId() startMethod");
        try {
            return ResponseEntity.ok(userServices.deleteId(id));
        } catch (AccountException e) {
            return generatorResponse(e);
        }
    }

    private ResponseEntity generatorResponse(AccountException e) {
        log.info("AccountResourceImpl:generateResponse() startMethod");
        if(e.getMessage().equals("unautorized")){
            return ResponseEntity.status(401).body("Unauthorized");}
        return ResponseEntity.status(400).body("Bad request");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        log.info("AccountResourceImpl:test() startMethod");
        JwtDto jwtDto = AuthUtil.getJwtDto();
        UUID userId = AuthUtil.getUserId();
        System.out.println(jwtDto);
        System.out.println(userId);
        return ResponseEntity.ok("hello from test method");
    }
}
