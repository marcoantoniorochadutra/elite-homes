package com.elitehomes.service.base;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.domain.entity.Attachment;
import com.elitehomes.domain.repository.AttachmentRepository;
import com.elitehomes.domain.repository.gcp.BucketRepository;

public interface AttachmentService {

    default AttachmentRepository getAttachmentRepository() {
        throw new RuntimeException(AttachmentRepository.class.getSimpleName() + " not defined");
    }

    default Attachment saveFile(Long id, String filename, LoginDto login) {

        return null;
    }

    default Attachment removeFile(Long id, String filename, LoginDto login) {

        return null;
    }

}
