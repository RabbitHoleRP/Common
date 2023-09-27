package br.com.rabbithole.common.core.inventory.actions;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface InventoryCloseAction {
    void onClose(InventoryCloseEvent event);
}
