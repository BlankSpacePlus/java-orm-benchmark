package com.blankspace.orm.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

import com.blankspace.orm.dao.PersonJdbcDao;

public class OrmHelper {

    private OrmHelper() {
    }

    private static final String PROPERTIES_FILE_NAME = Objects.requireNonNull(PersonJdbcDao.class.getResource("../../../../../classes/")).getPath().substring(1) + "mysql.properties";

    public static final String DRIVER_NAME;

    public static final String DATABASE_URL;

    public static final String USER_NAME;

    public static final String USER_PASSWORD;

    static {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(PROPERTIES_FILE_NAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER_NAME = properties.getProperty("datasource.driver-class-name");
        DATABASE_URL = properties.getProperty("datasource.url");
        USER_NAME = properties.getProperty("datasource.username");
        USER_PASSWORD = properties.getProperty("datasource.password");
    }

}
