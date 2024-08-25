package edu.school21.chat.Main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private DataSource() {

    }

    static {
        config.setJdbcUrl(ConnectionData.url );
        config.setUsername(ConnectionData.user);
        config.setPassword(ConnectionData.passwd);
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        return ds.getConnection();
    }

}
