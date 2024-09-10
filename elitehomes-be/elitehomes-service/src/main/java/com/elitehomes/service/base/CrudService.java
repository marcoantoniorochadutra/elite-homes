package com.elitehomes.service.base;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.entity.base.MessageDto;

public interface CrudService<M> {

	M create(M model, LoginDto login);

	M update(Long id, M model, LoginDto login);

	MessageDto delete(Long id, LoginDto login);

	M findById(Long id, LoginDto login);

}
