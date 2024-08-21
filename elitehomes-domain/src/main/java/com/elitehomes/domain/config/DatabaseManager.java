package com.elitehomes.domain.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class DatabaseManager {



    @Autowired
    public DatabaseManager() {

    }

    public void createDatabase(String schema, String name) {
        insertRealEstate(schema, name);



    }

    private void insertRealEstate(String schema, String name) {


    }
}
