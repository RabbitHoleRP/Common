package br.com.rabbithole.common.core.sql;

import java.util.Optional;

/**
 * Classe padrão para criação de tabelas.
 *
 * @author Felipe Ros
 * @since 1.1.20
 * @version 1.0.0
 *
 * @param <K> Tipo da chave de pesquisa da Tabela.
 * @param <V> Objeto de informação da Tabela.
 */
public interface Data<K, V> {

    /**
     * Método para criação da Tabela.
     *
     * @return "True" caso seja criada com sucesso, "False" caso ocorra um erro.
     */
    public boolean createTable();

    /**
     * Método para inserir dados na Tabela.
     *
     * @param value Objeto que será inserido na Tabela.
     * @return "True" caso seja inserido com sucesso, "False" caso ocorra um erro.
     */
    public boolean insert(V value);

    /**
     * Método para verificar existência de um dado na Tabela.
     *
     * @param key chave para pesquisa.
     * @return "True" caso exista um registro, "False" caso não exista um registro ou ocorra um erro.
     */
    public boolean exists(K key);

    /**
     * Método para pegar um registro da Tabela.
     *
     * @param key chave para pesquisa.
     * @return {@link Optional} com o objeto caso a informação exista, {@link Optional} vazio caso informação não exista.
     */
    public Optional<V> get(K key);

    /**
     * Método para atualização de informações na tabela.
     *
     * @param key chave para atualização.
     * @param value valor que será atualizado.
     * @return "True" caso seja atualizado, "False" caso ocorra um erro.
     */
    public boolean update(K key, V value);

    /**
     * Método para deletar uma informação na Tabela.
     *
     * @param key chave da informação.
     * @return "True" caso seja deletado, "False" caso ocorra um erro.
     */
    public boolean delete(K key);
}
