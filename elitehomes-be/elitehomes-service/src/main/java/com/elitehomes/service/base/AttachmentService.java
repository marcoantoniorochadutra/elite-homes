package com.elitehomes.service.base;

import com.elitehomes.core.auth.LoginDto;

public interface AttachmentService<M> {


    M savePhoto(Long id, String filename, byte[] file, LoginDto login);

    M deletePhoto(Long id, String filename, LoginDto login);

}
