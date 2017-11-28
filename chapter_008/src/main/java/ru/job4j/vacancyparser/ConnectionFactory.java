package ru.job4j.vacancyparser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Class for get database connection.
 * @author atrifonov.
 * @version 1.
 * @since 27.11.2017.
 */
public class ConnectionFactory {
    /**
     * Interface singleton.
     */
    private interface Singleton {
        /**
         * Instance ConnectionFactory.
         */
        ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    /**
     * Data source for connection.
     */
    private final DataSource dataSource;

    /**
     * Construct ConnectionFactory.
     */
    private ConnectionFactory() {
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "password"); // or get properties from some configuration file

        GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                "jdbc:postgresql://localhost:5432/first_base", properties
        );
        new PoolableConnectionFactory(
                connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED
        );

        this.dataSource = new PoolingDataSource(pool);
    }

    /**
     * Getting connection.
     * @return connection to database.
     * @throws SQLException exception.
     */
    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}