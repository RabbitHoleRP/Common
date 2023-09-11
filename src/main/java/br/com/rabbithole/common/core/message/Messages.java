package br.com.rabbithole.common.core.message;

import br.com.rabbithole.common.utils.StringUtils;
import org.bukkit.command.CommandSender;
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
     * Envia uma mensagem de Erro para a Instância que ativou o evento.
     *
     * @param commandSender Instância que receberá a mensagem.
     * @param errorMessage Mensagem de erro que será enviada.
     */
    public void sendError(@NotNull CommandSender commandSender, @NotNull String errorMessage) {
        commandSender.sendMessage(StringUtils.format("<red>" + errorMessage));
    }

    /**
     * Enviar uma mensagem de Aviso para a Instância que ativou o evento.
     *
     * @param commandSender Instância que receberá a mensagem.
     * @param warnMessage Mensagem de aviso que será enviada.
     */
    public void sendWarn(@NotNull CommandSender commandSender, @NotNull String warnMessage) {
        commandSender.sendMessage(StringUtils.format("<yellow>" + warnMessage));
    }

    /**
     * Enviar uma mensagem de Sucesso para a Instância que ativou o evento.
     *
     * @param commandSender Instância que receberá a mensagem.
     * @param successMessage Mensagem de sucesso que será enviada.
     */
    public void sendSuccess(@NotNull CommandSender commandSender, @NotNull String successMessage) {
        commandSender.sendMessage(StringUtils.format("<green>" + successMessage));
    }
}
