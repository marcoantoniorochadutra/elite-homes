package com.elitehomes.domain.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider<String> {

    private static final long serialVersionUID = 1L;

    private final DataSource dataSource;
    private final String defaultDatabase;

    @Autowired
    public MultiTenantConnectionProviderImpl(DataSource dataSource,
                                             @Value("${env.multitenant.db.default}") String defaultDatabase) {
        this.dataSource = dataSource;
        this.defaultDatabase = defaultDatabase;
        System.err.println("Building: " + defaultDatabase);
        TenantContext.setCurrentTenant(defaultDatabase);
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
            connection.createStatement().execute("use " + tenantIdentifier);
        } catch (SQLException e) {
            throw new HibernateException("Error setting to schema [" + tenantIdentifier + "]", e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) {
        try (connection) {
            connection.createStatement().execute("use " + defaultDatabase);
        } catch (SQLException e) {
            throw new HibernateException("Error setting default schema [" + defaultDatabase + "]", e);
        }
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }
}
