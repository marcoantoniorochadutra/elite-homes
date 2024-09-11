package com.elitehomes.service;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.auth.LoginWeb;
import com.elitehomes.core.constants.SystemConstants;
import com.elitehomes.core.exception.ref.LoginError;
import com.elitehomes.core.exception.LoginException;
import com.elitehomes.domain.entity.User;
import com.elitehomes.model.auth.AccountAcessDto;
import com.elitehomes.support.AbstractTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class AuthenticationServiceTest extends AbstractTestSupport {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void shouldLogin() {
        User u = userDefault();

        LoginWeb login = authenticationService.login(new AccountAcessDto(u.getEmail(), "Dificuldade$124%"), generateOrigin());

        assertNotNull(login.getAccessToken());
        assertNotNull(login.getRefreshToken());

        LoginDto dto = login.getLogin();
        assertEquals(u.getName(), dto.getName());
        assertEquals(u.getEmail(), dto.getEmail());
        assertEquals(SystemConstants.EN_LOCALE.toString(), dto.getLocale());
    }

    @Test
    public void shouldNotLoginDisabledUser() {
        User u = createUser("Claudio Arraiz", "maicon@hotmail.com", "123456&!#Marco");
        u.getUserDetails().setActive(false);
        userRepository.saveAndFlush(u);

        LoginException ex = assertThrows(LoginException.class,
                () -> authenticationService.login(new AccountAcessDto(u.getEmail(), "123456&!#Claudio"), generateOrigin()));


        assertEquals("User account is blocked. Please contact the system administrator.", ex.getMessage());
        assertEquals(LoginError.BLOCKED, ex.getError());
    }

    @Test
    public void shouldNotRegisterUserInvalidPass() {
        User u = createUser("Claudio Arraiz", "claudioarraiz@hotmail.com", "123456&!#Marco");

        LoginException ex = assertThrows(LoginException.class,
                () -> authenticationService.login(new AccountAcessDto(u.getEmail(), "123456&!#Claudio"), generateOrigin()));


        assertEquals("Invalid username or password.", ex.getMessage());
        assertEquals(LoginError.CREDENTIALS, ex.getError());
    }

}
