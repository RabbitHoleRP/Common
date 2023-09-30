package br.com.rabbithole.common.core.inventory;

import br.com.rabbithole.common.core.inventory.actions.InventoryCloseAction;
import br.com.rabbithole.common.core.inventory.implementation.InventoryImplementation;
import br.com.rabbithole.common.core.inventory.implementation.PaginationImplementation;
import org.bukkit.entity.Player;
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
                paginationImplementation.setForwardButtonAction((Player) event.getWhoClicked(), event);
            } else if (paginationImplementation.getBackButtonSlot() == event.getSlot()) {
                paginationImplementation.setBackButtonAction((Player) event.getWhoClicked(), event);
            } else if (inventoryAction.equals(InventoryAction.PICKUP_ALL) && paginationImplementation.getDefaultLeftClickAction() != null) {
                paginationImplementation.getDefaultLeftClickAction().onClick(event);
            } else if (inventoryAction.equals(InventoryAction.PICKUP_HALF) && paginationImplementation.getDefaultRightClickAction() != null) {
                paginationImplementation.getDefaultRightClickAction().onClick(event);
            } else if (Arrays.stream(paginationImplementation.getItemsPattern()).noneMatch(x -> x == event.getSlot()) && paginationImplementation.getRegisteredExtraItems().containsKey(event.getSlot())) {
                if (inventoryAction.equals(InventoryAction.PICKUP_ALL)) {
                    paginationImplementation.getRegisteredExtraLeftActions().get(event.getSlot()).onClick(event);
                } else if (inventoryAction.equals(InventoryAction.PICKUP_HALF)){
                    paginationImplementation.getRegisteredExtraRightActions().get(event.getSlot()).onClick(event);
                }
            }
        }
        /*
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
         */
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
