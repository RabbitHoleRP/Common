package br.com.rabbithole.common;

import br.com.rabbithole.common.core.inventory.InventoryListener;
import br.com.rabbithole.common.core.message.Messages;
import org.bukkit.plugin.Plugin;

/**
 * Classe de acesso da Biblioteca.
 *
 * @author Felipe Ros
 * @since 1.0.0
 * @version 1.0.1
 */
public final class Common {
    private final Messages messages;

    public Common(Plugin plugin) {
        messages = new Messages();
        new InventoryListener(plugin);
    }

    /**
     * Acesso do componente de Mensagens.
     *
     * @return Instância do componente de Mensagens.
     */
    public Messages getMessages() {
        return messages;
    }
}
