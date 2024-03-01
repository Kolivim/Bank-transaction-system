package ru.kolivim.myproject.task.management.system.impl.service.user;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountDto;
import ru.kolivim.myproject.task.management.system.api.dto.account.AccountSearchDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.AuthenticateDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.JwtDto;
import ru.kolivim.myproject.task.management.system.api.dto.auth.RegistrationDto;
import ru.kolivim.myproject.task.management.system.api.dto.user.UserDto;
import ru.kolivim.myproject.task.management.system.domain.account.Account;
import ru.kolivim.myproject.task.management.system.domain.role.Role;
import ru.kolivim.myproject.task.management.system.domain.user.User;
import ru.kolivim.myproject.task.management.system.domain.user.User_;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAccount;
import ru.kolivim.myproject.task.management.system.impl.mapper.account.MapperAuthenticate;
import ru.kolivim.myproject.task.management.system.impl.repository.user.EmailRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.PhoneRepository;
import ru.kolivim.myproject.task.management.system.impl.repository.user.UserRepository;
import ru.kolivim.myproject.task.management.system.impl.service.role.RoleService;
import ru.kolivim.myproject.task.management.system.impl.utils.auth.AuthUtil;
import ru.kolivim.myproject.task.management.system.impl.repository.account.AccountRepository;
import ru.kolivim.myproject.task.management.system.api.dto.search.BaseSearchDto;
import ru.kolivim.myproject.task.management.system.impl.utils.specification.SpecificationUtils;

import javax.security.auth.login.AccountException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;
    private final MapperAuthenticate mapperAuthenticate;
    private final MapperAccount mapperAccount;

    /** Исходник: */
    private static final String BADREUQEST = "bad reqest";
    private final AccountRepository accountRepository;
    private final RoleService roleService;

    /** Ниже начало методов: */

    /** Определиться - стоит ли сделать метод синхронизированным? Посмотреть к с друзьями сделали синхронизацию в SN!!! */
    public Boolean doesUserDataExist(RegistrationDto registrationDto) {
        log.info("UserService: doesUserDataExist(RegistrationDto registrationDto) startMethod, RegistrationDto: {}",
                registrationDto);

        /*
        Specification userSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(User_.LOGIN, registrationDto.getLogin()));
        List<User> users = userRepository.findAll(userSpecification);
        */

        Optional<User> user = userRepository.findByLoginAndIsDeletedFalse(registrationDto.getLogin());
        int countPhone = phoneRepository.countByPhone(registrationDto.getPhone());
        int countEmail = emailRepository.countByEmail(registrationDto.getEmail());

        log.info("UserService: doesUserDataExist(*) received countPhone: {}, countEmail: {}, " +
                        "Optional<User> :{}", countPhone, countEmail, user);

        return !user.isEmpty() || countPhone !=0 || countEmail != 0;

        /** Исходник: */
        /*
        return accountRepository.findFirstByEmail(email).isPresent();
        */
        //return null;

    }

    private BaseSearchDto getBaseSearchDto(){
        BaseSearchDto baseSearchDto = new BaseSearchDto();
        baseSearchDto.setIsDeleted(false);
        return  baseSearchDto;
    }


    public UserDto create(RegistrationDto registrationDto) throws AccountException {
        log.info("UserService:create(RegistrationDto registrationDto) startMethod, RegistrationDto: {}",
                registrationDto);


        Account account = accountRepository.save(mapperAccount.toAccount(registrationDto));
        return mapperAuthenticate.toUserDTO(account);

        /*
        Account account = mapperAccount.toEntity(accountDto);
        account.setRegistrationDate(LocalDateTime.now(ZoneId.of("Z")));
        account.setRoles(roleService.getRoleSet(Arrays.asList("USER", "MODERATOR")));
        account = accountRepository.save(account);
        return mapperAccount.toDto(account);
        */
    }

    /** Ниже остались в исходном виде: */

    public AccountDto create(AccountDto accountDto) throws AccountException {
        log.info("AccountService:create() startMethod");
        /*
        Account account = mapperAccount.toEntity(accountDto);
        account.setRegistrationDate(LocalDateTime.now(ZoneId.of("Z")));
        account.setRoles(roleService.getRoleSet(Arrays.asList("USER", "MODERATOR")));
        account = accountRepository.save(account);
        return mapperAccount.toDto(account);
        */
        return null;
    }

    public AccountDto update(AccountDto accountDto) throws AccountException {
        log.info("AccountService:putMe() startMethod");
        /*
        Account account = accountDto.getId() != null ? accountRepository
                .findById(accountDto.getId()).get() : accountRepository.findById(AuthUtil.getUserId()).get();
        account = mapperAccount.rewriteEntity(accountRepository.findById(account.getId()).get(), accountDto);
        accountRepository.save(account);
        return mapperAccount.toDto(account);
                */
        return null;
    }

    public AccountDto getByEmail(String email) throws AccountException {
        log.info("AccountService:get(String email) startMethod");
        /*
        return mapperAccount.toDto(accountRepository.findFirstByEmail(email).orElseThrow(() -> new AccountException("BADREUQEST")));
        */
        return null;
    }

    public AccountDto getId(UUID uuid) throws AccountException {
        log.info("AccountService:get(String email) startMethod");
        /*
        return mapperAccount.toDto(accountRepository.findById(uuid).orElseThrow(() -> new AccountException("BADREUQEST")));
        */
        return null;
    }

    public AccountDto getMe() throws AccountException {
        log.info("AccountService: getMe() startMethod");
        /*
        return mapperAccount.toDto(accountRepository.findById(AuthUtil.getUserId()).orElseThrow(() -> new AccountException(BADREUQEST)));
        */
        return null;
    }

    /*
    public AccountDto changePassword(PasswordChangeDto passwordChangeDtoDto) {
        if (!passwordChangeDtoDto.getNewPassword1().equals(passwordChangeDtoDto.getNewPassword2())) {
            new AccountException("введенные пароли не совпадают");
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setPassword(passwordChangeDtoDto.getNewPassword1());
        accountDto.setId(AuthUtil.getUserId());
        return update(accountDto);
    }

    public AccountDto changeEmail(ChangeEmailDto changeEmailDto) {
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(changeEmailDto.getEmail().getEmail());
        accountDto.setId(AuthUtil.getUserId());
        return update(accountDto);
    }
    */


    public Page<AccountDto> getAll(AccountSearchDto accountSearchDto, Pageable pageable) throws AccountException {
        /*
        Specification spec = SpecificationUtils.equal(Account_.COUNTRY, accountSearchDto.getCountry())
                .or(SpecificationUtils.like(Account_.FIRST_NAME, accountSearchDto.getFirstName()))
                .or(SpecificationUtils.like(Account_.LAST_NAME, accountSearchDto.getLastName()))
                .or(SpecificationUtils.like(Account_.CITY, accountSearchDto.getCity()))
                .or(SpecificationUtils.equal(Account_.EMAIL, accountSearchDto.getEmail()))
                .or(SpecificationUtils.between(Account_.BIRTH_DATE, accountSearchDto.getAgeFrom(), accountSearchDto.getAgeTo()))
                .or(SpecificationUtils.in(Account_.ID, accountSearchDto.getIds()));
        Page<Account> accounts = accountRepository.findAll(spec, pageable);
        return accounts.map(mapperAccount::toDto);
        */
        return null;
    }

    public AccountDto putMe(AccountDto accountDto) throws AccountException {
        log.info("AccountService:putMe() startMethod");
        /*
        return mapperAccount.toDto(mapperAccount.rewriteEntity(accountRepository.findById(AuthUtil.getUserId()).get(), accountDto));
        */
        return null;
    }

    public boolean delete() throws AccountException {
        log.info("AccountService:delete() startMethod");
        accountRepository.deleteById(AuthUtil.getUserId());
        return true;
    }

    public boolean deleteId(UUID id) throws AccountException {
        log.info("AccountService:deleteId() startMethod");
        accountRepository.deleteById(id);
        return true;
    }


    public JwtDto getJwtDto(AuthenticateDto authenticateDto) {
        log.info("AccountService:getJwtDto() startMethod");

        /*
        Optional<Account> account = accountRepository.findFirstByEmail(authenticateDto.getEmail());
        Assert.isTrue(account.isPresent());
        Assert.isTrue(account.get().getPassword().equals(authenticateDto.getPassword()));
        JwtDto jwtDto = new JwtDto();
        jwtDto.setId(account.get().getId());
        jwtDto.setEmail(account.get().getEmail());
        jwtDto.setRoles(listOfRolesFromSetOfRoles(account.get().getRoles()));
        account.get().setLastOnlineTime(LocalDateTime.now());
        */

        Optional<User> user = userRepository.findByLogin(authenticateDto.getLogin());
        Assert.isTrue(user.isPresent());
        Assert.isTrue(user.get().getPassword().equals(authenticateDto.getPassword()));
        JwtDto jwtDto = new JwtDto();
        jwtDto.setId(user.get().getId());
        jwtDto.setEmail(user.get().getLogin());
//        jwtDto.setRoles(listOfRolesFromSetOfRoles(user.get().getRoles()));

        return jwtDto;
    }

    public Boolean doesAccountWithSuchEmailExist(String email) {
        /*
        return accountRepository.findFirstByEmail(email).isPresent();
        */
        return null;
    }

    private List<String> listOfRolesFromSetOfRoles(Set<Role> roles) {
        log.info("AccountService:listOfRolesFromSetOfRoles() startMethod");
        ArrayList<String> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getRole());
        }
        return roleNames;
    }

    public Account getAccountByEmail(String email) {
        /*
        return accountRepository.findFirstByEmail(email).orElse(null);
        */
        return null;
    }

    public void save(Account account) {
        accountRepository.save(account);
    }
}
