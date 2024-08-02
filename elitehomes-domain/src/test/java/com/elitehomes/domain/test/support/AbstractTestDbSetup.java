package com.elitehomes.domain.test.support;

import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@Transactional
public abstract class AbstractTestDbSetup {

	private static final Logger log = LoggerFactory.getLogger(AbstractTestDbSetup.class);


	private static final MySqlContainer mysqlContainer = new MySqlContainer(MySQLTestImages.MYSQL_80_IMAGE)
			.withUsername("root")
			.withPassword("root")
			.withDatabaseName("teste")
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

}
