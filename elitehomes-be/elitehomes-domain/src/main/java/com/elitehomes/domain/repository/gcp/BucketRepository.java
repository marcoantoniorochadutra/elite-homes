package com.elitehomes.domain.repository.gcp;

import com.elitehomes.domain.repository.AttachmentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BucketRepository implements AttachmentRepository {

    @Override
    public String savePublic(String filename, byte[] var2, String bucket) {
        return null;
    }

    @Override
    public boolean delete(String bucket, String fileName) {
        return false;
    }
}
