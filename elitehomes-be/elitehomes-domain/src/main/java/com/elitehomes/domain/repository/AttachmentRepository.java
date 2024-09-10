package com.elitehomes.domain.repository;

import java.io.IOException;

public interface AttachmentRepository {

    String savePublic(String filename, byte[] var2, String bucket);

    boolean delete(String bucket, String fileName);
}
