package com.elitehomes.service;

import com.elitehomes.core.auth.LoginOrigin;
import com.elitehomes.core.auth.LoginWeb;
import com.elitehomes.model.auth.AccountAcessDto;

public interface AuthenticationService {

    LoginWeb login(AccountAcessDto userDto, LoginOrigin origin);
}
