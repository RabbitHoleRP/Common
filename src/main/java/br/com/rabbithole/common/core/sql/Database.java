package br.com.rabbithole.common.core.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private final HikariDataSource hikariDataSource;

    public Database(String host, int port, String database, String user, String password) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        this.hikariDataSource = new HikariDataSource(createConfig(url, user, password));
    }

    private HikariConfig createConfig(String url, String user, String password) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty("CachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("PrepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.setMaximumPoolSize(120);
        hikariConfig.setMaxLifetime(30000);
        hikariConfig.setLeakDetectionThreshold(5000);
        hikariConfig.setMinimumIdle(3);
        return hikariConfig;
    }

    public Connection getConnection() throws SQLException {
        return this.hikariDataSource.getConnection();
    }

    public Query executeQuery(String sql) throws SQLException {
        Connection connection = this.getConnection();
        return new Query(connection, connection.prepareStatement(sql));
    }
}
