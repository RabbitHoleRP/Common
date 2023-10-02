package br.com.rabbithole.common.core.inventory;

import br.com.rabbithole.common.core.inventory.actions.InventoryCloseAction;
import br.com.rabbithole.common.core.inventory.implementation.InventoryImplementation;
import br.com.rabbithole.common.core.inventory.implementation.PaginationImplementation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class InventoryListener implements Listener {
    final Plugin plugin;

    public InventoryListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        InventoryAction inventoryAction = event.getAction();

        if (inventory.getHolder(false) instanceof InventoryImplementation inventoryImplementation) {
            event.setCancelled(true);

            if (inventoryAction.equals(InventoryAction.PICKUP_ALL)) {
                if (inventoryImplementation.getRegisteredLeftClickActions().containsKey(event.getSlot()))
                    inventoryImplementation.getRegisteredLeftClickActions().get(event.getSlot()).onClick(event);
            } else if (inventoryAction.equals(InventoryAction.PICKUP_HALF)) {
                if (inventoryImplementation.getRegisteredRightClickActions().containsKey(event.getSlot()))
                    inventoryImplementation.getRegisteredRightClickActions().get(event.getSlot()).onClick(event);
            }
        } else if (inventory.getHolder(false) instanceof PaginationImplementation paginationImplementation) {
            event.setCancelled(true);

            if (paginationImplementation.getForwardButtonSlot() == event.getSlot()) {
                event.getWhoClicked().sendMessage("VC TA CLICANDO NO FORWARD!");
                paginationImplementation.executeForwardAction(event);
            } else if (paginationImplementation.getBackButtonSlot() == event.getSlot()) {
                event.getWhoClicked().sendMessage("VC TA CLICANDO NO BACK!");
                paginationImplementation.executeBackAction(event);
            } else if (inventoryAction.equals(InventoryAction.PICKUP_ALL) && paginationImplementation.getDefaultLeftClickAction() != null && Arrays.stream(paginationImplementation.getItemsPattern()).anyMatch(x -> x == event.getSlot())) {
                paginationImplementation.getDefaultLeftClickAction().onClick(event);
            } else if (inventoryAction.equals(InventoryAction.PICKUP_HALF) && paginationImplementation.getDefaultRightClickAction() != null && Arrays.stream(paginationImplementation.getItemsPattern()).anyMatch(x -> x == event.getSlot())) {
                paginationImplementation.getDefaultRightClickAction().onClick(event);
            } else if (paginationImplementation.getCustomButtons().stream().anyMatch(button -> button.slot() == event.getSlot())) {
                if (inventoryAction.equals(InventoryAction.PICKUP_ALL) && paginationImplementation.getCustomButtonLeftClickActions().containsKey(event.getSlot())) {
                    paginationImplementation.getCustomButtonLeftClickActions().get(event.getSlot()).onClick(event);
                } else if (inventoryAction.equals(InventoryAction.PICKUP_HALF) && paginationImplementation.getCustomButtonRightClickActions().containsKey(event.getSlot())) {
                    paginationImplementation.getCustomButtonRightClickActions().get(event.getSlot()).onClick(event);
                }
            }
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
