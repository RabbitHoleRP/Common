package br.com.rabbithole.common.core.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class InventoryListener implements Listener {
    final Plugin plugin;
    private final InventoryManager inventoryManager;

    public InventoryListener(Plugin plugin, InventoryManager inventoryManager) {
        this.plugin = plugin;
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Inventory targetInventory = event.getInventory();
        if (!this.inventoryManager.isMember(targetInventory)) {
            Bukkit.getConsoleSender().sendMessage("Nenhum inventário corresponde!");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("Algum inventário corresponde!");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        Inventory targetInventory = event.getInventory();
        InventoryAction inventoryAction = event.getAction();

        if (!this.inventoryManager.isMember(targetInventory)) {
            Bukkit.getConsoleSender().sendMessage("Nenhum inventário corresponde!");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("Algum inventário corresponde!");


        //PEGAR SLOT DO ITEM CLICADO E O ITEM CLICADO E COMPARAR COM O ITEM NO INVENTÁRIO X

    }
}
