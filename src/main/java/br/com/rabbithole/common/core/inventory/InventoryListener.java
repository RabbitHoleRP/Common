package br.com.rabbithole.common.core.inventory;

import br.com.rabbithole.common.core.inventory.actions.InventoryCloseAction;
import br.com.rabbithole.common.core.inventory.implementation.InventoryImplementation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class InventoryListener implements Listener {
    final Plugin plugin;

    public InventoryListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (!((inventory.getHolder(false)) instanceof InventoryImplementation inventoryImplementation)) return;
        event.setCancelled(true);

        InventoryAction action = event.getAction();
        if (action.equals(InventoryAction.PICKUP_ALL)) {
            if (inventoryImplementation.getRegisteredLeftClickActions().containsKey(event.getSlot()))
                inventoryImplementation.getRegisteredLeftClickActions().get(event.getSlot()).onClick(event);
        } else if (action.equals(InventoryAction.PICKUP_HALF)) {
            if (inventoryImplementation.getRegisteredRightClickActions().containsKey(event.getSlot()))
                inventoryImplementation.getRegisteredRightClickActions().get(event.getSlot()).onClick(event);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (!((inventory.getHolder(false)) instanceof InventoryImplementation inventoryImplementation)) return;

        for (InventoryCloseAction closeAction : inventoryImplementation.getRegisteredCloseActions()) {
            closeAction.onClose(event);
        }
    }
}
