package br.com.rabbithole.common.core.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe de definição do Banco de Dados
 */
public final class Database {
    private final HikariDataSource hikariDataSource;

    /**
     * Construtor da conexão do Banco de Dados.
     *
     * @param host Endereço de conexão.
     * @param port Porta de conexão.
     * @param database Base de dados utilizada.
     * @param user Usuário de conexão.
     * @param password Senha de conexão.
     */
    public Database(String host, int port, String database, String user, String password) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        this.hikariDataSource = new HikariDataSource(createConfig(url, user, password));
    }

    /**
     * Construção da configuração da "pool" de conexões.
     *
     * @param url URL de conexão.
     * @param user Usuário de conexão.
     * @param password Senha de conexão.
     * @return {@link HikariConfig} configuração da "pool" de conexões.
     */
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

    /**
     * Retorna uma conexão da "pool" de conexões.
     *
     * @return {@link Connection} conexão do banco de dados.
     * @throws SQLException conexão indisponível.
     */
    public Connection getConnection() throws SQLException {
        return this.hikariDataSource.getConnection();
    }

    /**
     * Retorna um objeto {@link Query}.
     *
     * @param sql consulta que será realizada.
     * @return {@link Query} objeto de consulta.
     * @throws SQLException erro ao executar consulta.
     */
    public Query executeQuery(String sql) throws SQLException {
        Connection connection = this.getConnection();
        return new Query(connection, connection.prepareStatement(sql));
    }
}
