package ru.kolivim.myproject.task.management.system.impl.service.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.user.Email;
import ru.kolivim.myproject.task.management.system.domain.user.Phone;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAccount;
import ru.kolivim.myproject.task.management.system.impl.mapper.user.EmailMapper;
import ru.kolivim.myproject.task.management.system.impl.mapper.user.PhoneMapper;
import ru.kolivim.myproject.task.management.system.impl.repository.account.AccountRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.EmailRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.PhoneRepository;


import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;


    private final MapperAccount mapperAccount;
    private final PhoneMapper phoneMapper;
    private final EmailMapper emailMapper;

    private static final int MAX_PERCENT = 207;
    private static final  int INCREASE_PERCENT = 5;


    public Boolean create(RegistrationDto registrationDto) {
        log.info("AccountService: create(RegistrationDto registrationDto), registrationDto: {}",
                registrationDto);

        Account account = accountRepository.save(mapperAccount.toAccount(registrationDto, getMaxBalance(registrationDto)));
        Phone phone = phoneRepository.save(phoneMapper.toPhone(registrationDto, account.getId()));
        Email email = emailRepository.save(emailMapper.toEmail(registrationDto, account.getId()));

        log.info("AccountService: create(*) endMethod, Account: {}, Phone: {}, Email: {}",
                account, phone, email);
        return true;
    }

    @Scheduled(cron = "${cron.addPercent}")
    public void addPercent() {
        log.info("AccountService: addPercent() startMethod");
        List<Account> accountList = accountRepository.findAll();
        log.info("AccountService: addPercent() получен List<Account> accountList: {}", accountList);
        accountList.stream()
                .filter(acc -> acc.getIsDeleted() == false)
                .filter(acc -> incrBalance(acc.getBalance()) <= acc.getMaxBalance())
                .forEach(acc -> {
                    acc.setBalance(incrBalance(acc.getBalance()));
                    log.info("AccountService: addPercent() сохранен Account: {}", accountRepository.save(acc));});
        accountRepository.saveAll(accountList);
    }

    public synchronized boolean pay(UUID userToPay, UUID user, Double sum) {
        log.info("AccountService: pay(UUID userToPay, UUID user) startMethod, UUID userToPay : {}, UUID user: {}" ,
                userToPay, user);

        Account account = accountRepository.findById(user).orElseThrow();
        Account accountTo = accountRepository.findById(userToPay).orElseThrow();

        Double sumAfter = account.getBalance() - sum;
        Double sumAfterTo = accountTo.getBalance() + sum;

        if(sumAfter > 0 && !userToPay.equals(user)) {
            account.setBalance(sumAfter);
            accountTo.setBalance(sumAfterTo);
            accountRepository.save(account);
            accountRepository.save(accountTo);
        } else {
            return false;
        }

        return false;
    }

    private double getMaxBalance(RegistrationDto registrationDto) {
        log.info("AccountService: getMaxBalance(RegistrationDto registrationDto), registrationDto: {}",
                registrationDto);

        double maxBalance = registrationDto.getBalance() * MAX_PERCENT / 100;

        log.info("AccountService: getMaxBalance(*) endMethod, mxBalance: {}", maxBalance);
        return maxBalance;

    }

    private double incrBalance(Double balance) {
        log.info("AccountService: incrBalance(Double balance), Double: {}", balance);
        balance = balance + (balance * INCREASE_PERCENT / 100);
        log.info("AccountService: incrBalance(*), in out balance: {}", balance);
        return balance;

    }

}



