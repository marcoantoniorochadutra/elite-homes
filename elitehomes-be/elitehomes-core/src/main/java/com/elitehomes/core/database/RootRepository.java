package com.elitehomes.core.database;

import com.elitehomes.core.entity.base.MessageDto;

public interface RootRepository {

    void createSchema(String schema);

    void repairSchema(String schema);

}
