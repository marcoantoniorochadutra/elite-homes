package com.elitehomes.core.database.repository;

import com.elitehomes.core.database.RootRepository;
import com.elitehomes.core.entity.base.MessageDto;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RootRepositoryJDBC implements RootRepository {

    private static final Logger log = LoggerFactory.getLogger(RootRepositoryJDBC.class);

    private final DataSource dataSource;

    @Autowired
    public RootRepositoryJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createSchema(String schema) {
        MigrateResult result = Flyway.configure()
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .schemas(schema)
                .load()
                .migrate();

        log.info("Schema Created: {} | Schema: {} | {} changeset {} -> {} | Success: {} | Flyway version: {} | Warnings: {}",
                result.database, result.schemaName, result.migrationsExecuted,
                result.initialSchemaVersion, result.targetSchemaVersion, result.success,
                result.flywayVersion, result.warnings);

    }

    @Override
    public void repairSchema(String schema) {

    }
}
