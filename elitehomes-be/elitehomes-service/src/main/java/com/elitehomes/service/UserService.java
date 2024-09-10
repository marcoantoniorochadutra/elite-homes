package com.elitehomes.service;

import com.elitehomes.domain.entity.User;

public interface UserService {

    User registerUser(String nome, String email, String password);

}
