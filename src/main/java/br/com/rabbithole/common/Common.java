package br.com.rabbithole.common;

import br.com.rabbithole.common.core.inventory.InventoryManager;
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
    private final InventoryManager inventoryManager;

    public Common(Plugin plugin) {
        messages = new Messages();
        inventoryManager = new InventoryManager(plugin);
    }

    /**
     * Acesso do componente de Mensagens.
     *
     * @return Instância do componente de Mensagens.
     */
    public Messages getMessages() {
        return messages;
    }

    /**
     * Acesso do componente de Inventários.
     *
     * @return Instância do componente de Inventários.
     */
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
