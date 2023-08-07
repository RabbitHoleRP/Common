package br.com.rabbithole.common.core;

import br.com.rabbithole.common.utils.StringUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de padronização de envio das mensagens.
 *
 * @author Felipe Ros
 * @since 1.0.0
 * @version 1.0.0
 */
public class Messages {

    /**
     * Envia uma mensagem de Erro para o Jogador
     *
     * @param player Jogador que receberá a mensagem.
     * @param errorMessage Mensagem de erro que será enviada.
     */
    public void sendError(@NotNull Player player, @NotNull String errorMessage) {
        player.sendMessage(StringUtils.format("<red>" + errorMessage));
    }

    /**
     * Enviar uma mensagem de Aviso para o Jogador
     *
     * @param player Jogador que receberá a mensagem.
     * @param warnMessage Mensagem de aviso que será enviada.
     */
    public void sendWarn(@NotNull Player player, @NotNull String warnMessage) {
        player.sendMessage(StringUtils.format("<yellow>" + warnMessage));
    }

    /**
     * Enviar uma mensagem de Sucesso para o Jogador.
     *
     * @param player Jogador que receberá a mensagem.
     * @param successMessage Mensagem de sucesso que será enviada.
     */
    public void sendSuccess(@NotNull Player player, @NotNull String successMessage) {
        player.sendMessage(StringUtils.format("<green>" + successMessage));
    }
}
