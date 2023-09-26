package br.com.rabbithole.common.core.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de definição da Consulta de Banco de Dados.
 */
public final class Query implements AutoCloseable {
    private final Connection connection;
    private final PreparedStatement statement;
    private ResultSet resultSet = null;

    /**
     * Construção da Consulta do Banco de Dados.
     *
     * @param connection Conexão com o Banco de Dados.
     * @param statement Estrutura de Consulta do Banco de Dados.
     */
    public Query(Connection connection, PreparedStatement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    /**
     * Retorna a Conexão com o Banco de Dados.
     *
     * @return {@link Connection} conexão com o Bnaco de Dados.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Retorna a Estrutura de Consulta do Banco de Dados.
     *
     * @return {@link java.sql.Statement} estrutura de consulta.
     */
    public PreparedStatement getStatement() {
        return statement;
    }

    /**
     * Retorna o Resultado da Consulta no Banco de Dados.
     *
     * @return {@link ResultSet} resultado da consulta.
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * Execução da Consulta no Banco de Dados.
     *
     * @return {@link ResultSet} resultado da consulta.
     * @throws SQLException erro ao executar consulta.
     */
    public ResultSet executeQuery() throws SQLException {
        return this.resultSet = this.statement.executeQuery();
    }

    /**
     * Execução de atualização no Banco de Dados.
     *
     * @throws SQLException erro ao executar atualização.
     */
    public int executeUpdate() throws SQLException {
        return this.statement.executeUpdate();
    }

    /**
     * Implementação de {@link AutoCloseable}.
     *
     * @throws Exception erro ao retornar recursos.
     */
    @Override
    public void close() throws Exception {
        if (this.resultSet != null) this.resultSet.close();
        if (this.statement != null) this.statement.close();
        if (this.connection != null) this.connection.close();
    }
}
