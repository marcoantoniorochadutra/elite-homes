package com.elitehomes.domain.test.support;

import org.testcontainers.utility.DockerImageName;

public class MySQLTestImages {

	public static final DockerImageName MYSQL_56_IMAGE = DockerImageName.parse("mysql:5.6.51");

    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7.34");

    public static final DockerImageName MYSQL_80_IMAGE = DockerImageName.parse("mysql:8.0.24");

    public static final DockerImageName KEEK_MYSQL_IMAGE_LATEST = DockerImageName.parse("us-docker.pkg.dev/keek-track/selsyn/keek-teste-db:latest").asCompatibleSubstituteFor("mysql:8.0.24");


}
