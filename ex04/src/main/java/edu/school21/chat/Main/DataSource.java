package edu.school21.chat.Main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private DataSource() {

    }

    static {
        config.setJdbcUrl(Application.get("db.url"));
        config.setUsername(Application.get("db.login"));
        config.setPassword(Application.get("db.password"));
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException  {
        return ds.getConnection();
    }

}