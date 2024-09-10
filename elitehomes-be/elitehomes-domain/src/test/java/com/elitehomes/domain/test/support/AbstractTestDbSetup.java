package com.elitehomes.domain.test.support;

import com.elitehomes.domain.config.TenantContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;


@Testcontainers
@Transactional
public abstract class AbstractTestDbSetup {

	private static final Logger log = LoggerFactory.getLogger(AbstractTestDbSetup.class);

	private static final String DATABASE_TEST = "testedb";

	private static final MySqlContainer mysqlContainer = new MySqlContainer(MySQLTestImages.MYSQL_80_IMAGE)
			.withUsername("root")
			.withPassword("root")
			.withDatabaseName(DATABASE_TEST)
			.withInitScript("db_root/migration/V1_000__create-root-tables.sql")
			.withUrlParam("serverTimezone", "UTC")
			.withUrlParam("TC_REUSABLE", "true")
			.withLogConsumer(new Slf4jLogConsumer(log));

	@BeforeAll
	static void startDb() {
		if (!mysqlContainer.isRunning()) {
			mysqlContainer.start();
			System.setProperty("DATASOURCE_URL", mysqlContainer.getJdbcUrl());
			System.setProperty("DATASOURCE_PASSWORD", mysqlContainer.getPassword());
			System.setProperty("DATASOURCE_USERNAME", mysqlContainer.getUsername());
			System.setProperty("HIBERNATE_DDL_OPTION", "none");
		}
	}

	@BeforeEach
	public void resetDb() throws IOException {
		TenantContext.setCurrentTenant(DATABASE_TEST);
	}

}
