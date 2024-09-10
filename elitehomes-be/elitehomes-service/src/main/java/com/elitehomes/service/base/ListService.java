package com.elitehomes.service.base;

import com.elitehomes.core.auth.LoginDto;

import java.util.List;
import java.util.Map;

public interface ListService<T> {

    List<T> listByCriteria(Map<String, Object> searchCriteria, LoginDto login);

}


