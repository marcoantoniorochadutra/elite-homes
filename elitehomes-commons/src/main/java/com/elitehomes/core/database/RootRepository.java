package com.elitehomes.core.database;

public interface RootRepository {

    void createSchema(String schema);

    void repairSchema(String schema);

}
