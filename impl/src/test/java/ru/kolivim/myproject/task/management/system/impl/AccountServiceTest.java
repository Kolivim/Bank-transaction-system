package ru.kolivim.myproject.task.management.system.impl;

//import ru.kolivim.myproject.task.management.system.api.dto.comment.CommentDto;
//import ru.kolivim.myproject.task.management.system.api.dto.task.PriorityType;
//import ru.kolivim.myproject.task.management.system.api.dto.task.StatusType;
//import ru.kolivim.myproject.task.management.system.domain.comment.Comment;
//import ru.kolivim.myproject.task.management.system.domain.task.Task;
//import ru.kolivim.myproject.task.management.system.impl.mapper.comment.CommentMapper;
//import ru.kolivim.myproject.task.management.system.impl.repository.comment.CommentRepository;
//import ru.kolivim.myproject.task.management.system.impl.repository.task.TaskRepository;
//import ru.kolivim.myproject.task.management.system.impl.service.comment.CommentService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.user.Phone;
import ru.kolivim.myproject.task.management.system.impl.repository.account.AccountRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.EmailRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.PhoneRepository;
import ru.kolivim.myproject.task.management.system.impl.service.account.AccountService;
import ru.kolivim.myproject.task.management.system.impl.utils.technikalUser.TechnicalUserConfig;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class AccountServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    JwtEncoder jwtEncoder;
    @Autowired
    TechnicalUserConfig technicalUser;
    @Autowired
    JwtEncoder accessTokenEncoder;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgres::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgres::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @Test
    @Transactional
    void test() {
        technicalUser.executeByTechnicalUser(() -> 2);
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Transactional
    @DisplayName("Test create account")
    public void create() {

        technicalUser.executeByTechnicalUser(() -> 1);

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setBirthDate(ZonedDateTime.now().minus(20, ChronoUnit.YEARS));
        registrationDto.setFullName("Борис Иванович Иванов");
        registrationDto.setLogin("two");
        registrationDto.setPassword("password");
        registrationDto.setPhone("89998887758");
        registrationDto.setEmail("zz@zz.ru");
        registrationDto.setBalance(1000.0);

        assertDoesNotThrow(() -> accountService.create(registrationDto));
        List<Account> accountList = accountRepository.findAll();
        assertTrue(accountList.size() !=0);
        Account account = accountRepository.findByLoginAndIsDeletedFalse("two").orElseThrow();
        assertThat(account).hasFieldOrPropertyWithValue("fullname", "Борис Иванович Иванов");
        assertThat(account).hasFieldOrPropertyWithValue("password", "password");
        assertThat(account).hasFieldOrPropertyWithValue("balance", 1000.0);
        assertDoesNotThrow(() -> phoneRepository.findByPhone("89998887758").orElseThrow());
        assertDoesNotThrow(() -> emailRepository.findByEmail("zz@zz.ru").orElseThrow());

    }


    @Test
    @Transactional
    @DisplayName("Test addPercent account")
    public void addPercent() {

        technicalUser.executeByTechnicalUser(() -> 1);

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setBirthDate(ZonedDateTime.now().minus(20, ChronoUnit.YEARS));
        registrationDto.setFullName("Борис Иванович Иванов");
        registrationDto.setLogin("two");
        registrationDto.setPassword("password");
        registrationDto.setPhone("89998887758");
        registrationDto.setEmail("zz@zz.ru");
        registrationDto.setBalance(1000.0);

        assertDoesNotThrow(() -> accountService.create(registrationDto));
        accountService.addPercent();
        List<Account> accountList = accountRepository.findAll();
        assertTrue(accountList.size() !=0);
        Account account = accountRepository.findByLoginAndIsDeletedFalse("two").orElseThrow();
        assertTrue(account.getBalance() > 1000.0);
    }

    @Test
    @Transactional
    @DisplayName("Test pay account")
    public void pay() {

        technicalUser.executeByTechnicalUser(() -> 1);

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setBirthDate(ZonedDateTime.now().minus(20, ChronoUnit.YEARS));
        registrationDto.setFullName("Борис Иванович Иванов");
        registrationDto.setLogin("two");
        registrationDto.setPassword("password");
        registrationDto.setPhone("89998887758");
        registrationDto.setEmail("zz@zz.ru");
        registrationDto.setBalance(1000.0);

        RegistrationDto registrationDto2 = new RegistrationDto();
        registrationDto2.setBirthDate(ZonedDateTime.now().minus(20, ChronoUnit.YEARS));
        registrationDto2.setFullName("Игорь Иванович Иванов");
        registrationDto2.setLogin("one");
        registrationDto2.setPassword("password");
        registrationDto2.setPhone("89108887758");
        registrationDto2.setEmail("xx@xx.ru");
        registrationDto2.setBalance(1000.0);

        assertDoesNotThrow(() -> accountService.create(registrationDto));
        assertDoesNotThrow(() -> accountService.create(registrationDto2));
        List<Account> accountList = accountRepository.findAll();
        assertTrue(accountList.size() !=0);
        Account account = accountRepository.findByLoginAndIsDeletedFalse("one").orElseThrow();
        Account account2 = accountRepository.findByLoginAndIsDeletedFalse("two").orElseThrow();
        accountService.pay(account2.getId(), account.getId(), 100.0);
        assertTrue(account.getBalance() == 900.0);
        assertTrue(account2.getBalance() == 1100.0);
    }

}

