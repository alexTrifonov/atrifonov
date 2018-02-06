package ru.job4j.fan;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class for get connection to database.
 * @author atrifonov
 * @version 1.
 * @since 29.01.2018.
 */
public class ConnectionFactory {
    /**
     * Logger.
     */
    private static final Logger CONNECTION_FACTORY = LogManager.getLogger(ConnectionFactory.class);
    /**
     * Instance of ConnectionFactory.
     */
    public static final ConnectionFactory INSTANCE = new ConnectionFactory();
    /**
     * DataSource.
     */
    private final PoolingDataSource<PoolableConnection> dataSource;

    /**
     * Construct ConnectionFactory.
     */
    private ConnectionFactory() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        String url = null;
        String user = null;
        String password = null;
        try {
            InputStream is = getClass().getResourceAsStream("config.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "windows-1251"));
            prop.load(br);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, user, password);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        dataSource = new PoolingDataSource<>(connectionPool);
        CONNECTION_FACTORY.info("ConnectionFactory create, dataSource create");
    }

    /**
     * Get connection.
     * @return connection.
     * @throws SQLException SQL exception.
     */
    public static Connection getDatabaseConnection() throws SQLException {
        return INSTANCE.dataSource.getConnection();
    }
}
