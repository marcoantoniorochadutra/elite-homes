package com.elitehomes.domain.test.support;

import java.util.HashSet;
import java.util.Set;


import jakarta.validation.constraints.NotNull;
import org.testcontainers.containers.ContainerLaunchException;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.utility.DockerImageName;

public class MySqlContainer extends JdbcDatabaseContainer<MySqlContainer>{

    private static final DockerImageName DEFAULT_IMAGE_NAME = MySQLTestImages.MYSQL_80_IMAGE;
    private static final String MY_CNF_CONFIG_OVERRIDE_PARAM_NAME = "TC_MY_CNF";
    private static final Integer MYSQL_PORT = 3306;
    private static final String DEFAULT_USER = "test";
    private static final String DEFAULT_PASSWORD = "pass";
    private String databaseName = "test";
    private String username = DEFAULT_USER;
    private String password = DEFAULT_PASSWORD;
    private static final String MYSQL_ROOT_USER = "root";

	public MySqlContainer() {
		this(DEFAULT_IMAGE_NAME);
	}
	
	public MySqlContainer(final DockerImageName dockerImageName) {
        super(dockerImageName);
        dockerImageName.assertCompatibleWith(DEFAULT_IMAGE_NAME);
        addExposedPort(MYSQL_PORT);
	}
	
	@NotNull
    @Override
    protected Set<Integer> getLivenessCheckPorts() {
        return new HashSet<>(getMappedPort(MYSQL_PORT));
    }

	@Override
	public String getDriverClassName() {
		return "com.mysql.cj.jdbc.Driver";
	}

	@Override
    public String getJdbcUrl() {
        String additionalUrlParams = constructUrlParameters("?", "&");
        return "jdbc:mysql://" + getHost() + ":" + getMappedPort(MYSQL_PORT) + "/" + databaseName + additionalUrlParams;
    }

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	protected String getTestQueryString() {
		return "select 1";
	}
	
	@Override
    protected void configure() {
        optionallyMapResourceParameterAsVolume(
            MY_CNF_CONFIG_OVERRIDE_PARAM_NAME,
            "/etc/mysql/conf.d",
            "mysql-default-conf"
        );

        addEnv("MYSQL_DATABASE", databaseName);
        if (!MYSQL_ROOT_USER.equalsIgnoreCase(username)) {
            addEnv("MYSQL_USER", username);
        }
        if (password != null && !password.isEmpty()) {
            addEnv("MYSQL_PASSWORD", password);
            addEnv("MYSQL_ROOT_PASSWORD", password);
        } else if (MYSQL_ROOT_USER.equalsIgnoreCase(username)) {
            addEnv("MYSQL_ALLOW_EMPTY_PASSWORD", "yes");
        } else {
            throw new ContainerLaunchException("Empty password can be used only with the root user");
        }
        setStartupAttempts(3);
    }

	
	@Override
    protected String constructUrlForConnection(String queryString) {
        String url = super.constructUrlForConnection(queryString);

        if (!url.contains("useSSL=")) {
            String separator = url.contains("?") ? "&" : "?";
            url = url + separator + "useSSL=false";
        }

        if (!url.contains("allowPublicKeyRetrieval=")) {
            url = url + "&allowPublicKeyRetrieval=true";
        }

        return url;
    }
	
	@Override
    public MySqlContainer withDatabaseName(final String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    @Override
    public MySqlContainer withUsername(final String username) {
        this.username = username;
        return this;
    }

    @Override
    public MySqlContainer withPassword(final String password) {
        this.password = password;
        return this;
    }
    
    public MySqlContainer withConfigurationOverride(String s) {
        parameters.put(MY_CNF_CONFIG_OVERRIDE_PARAM_NAME, s);
        return this;
    }

}
