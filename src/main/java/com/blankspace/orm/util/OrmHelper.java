package com.blankspace.orm.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

import com.blankspace.orm.dao.PersonJdbcDao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class OrmHelper {

    private OrmHelper() {
    }

    private static final String BASE_PROPERTIES_FILE = Objects.requireNonNull(PersonJdbcDao.class.getResource("../../../../../classes/")).getPath().substring(1);

    private static final String MYSQL_PROPERTIES_FILE = BASE_PROPERTIES_FILE + "mysql.properties";

    private static final String HIKARICP_PROPERTIES_FILE = BASE_PROPERTIES_FILE + "hikaricp.properties";

    public static final String DRIVER_NAME;

    public static final String DATABASE_URL;

    public static final String USER_NAME;

    public static final String USER_PASSWORD;

    private static final int HIKARICP_MINIMUM_IDLE;

    private static final int HIKARICP_MAXIMUM_POOL_SIZE;

    private static final int HIKARICP_CONNECTION_TIMEOUT;

    private static final boolean HIKARICP_AUTO_COMMIT;

    public static final HikariDataSource DATA_SOURCE;

    static {
        Properties mysqlProperties = new Properties();
        try {
            mysqlProperties.load(Files.newInputStream(Paths.get(MYSQL_PROPERTIES_FILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER_NAME = mysqlProperties.getProperty("datasource.driver-class-name");
        DATABASE_URL = mysqlProperties.getProperty("datasource.url");
        USER_NAME = mysqlProperties.getProperty("datasource.username");
        USER_PASSWORD = mysqlProperties.getProperty("datasource.password");
        Properties hikariProperties = new Properties();
        try {
            hikariProperties.load(Files.newInputStream(Paths.get(HIKARICP_PROPERTIES_FILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HIKARICP_MINIMUM_IDLE = Integer.parseInt(hikariProperties.getProperty("datasource.minimum-idle"));
        HIKARICP_MAXIMUM_POOL_SIZE = Integer.parseInt(hikariProperties.getProperty("datasource.maximum-pool-size"));
        HIKARICP_CONNECTION_TIMEOUT = Integer.parseInt(hikariProperties.getProperty("datasource.connection-timeout"));
        HIKARICP_AUTO_COMMIT = Boolean.parseBoolean(hikariProperties.getProperty("datasource.auto-commit"));
        DATA_SOURCE = initHikariDataSource();
    }

    private static HikariDataSource initHikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(DRIVER_NAME);
        config.setJdbcUrl(DATABASE_URL);
        config.setUsername(USER_NAME);
        config.setPassword(USER_PASSWORD);
        config.setMinimumIdle(HIKARICP_MINIMUM_IDLE);
        config.setMaximumPoolSize(HIKARICP_MAXIMUM_POOL_SIZE);
        config.setConnectionTimeout(HIKARICP_CONNECTION_TIMEOUT);
        config.setAutoCommit(HIKARICP_AUTO_COMMIT);
        return new HikariDataSource(config);
    }

}
