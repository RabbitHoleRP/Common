package br.com.rabbithole.common.core.inventory;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import com.google.common.annotations.Beta;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

@Beta
public class InventoryListener implements Listener {
    final Plugin plugin;
    private final InventoryManager inventoryManager;

    public InventoryListener(Plugin plugin, InventoryManager inventoryManager) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory targetInventory = event.getInventory();
        InventoryView inventoryView = event.getView();

        /*
        if (!this.inventoryManager.isRegistered(targetInventory)) {
            Bukkit.getConsoleSender().sendMessage("Nenhum inventário corresponde!");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("Algum inventário corresponde!");
         */

        if (!this.inventoryManager.isRegistered(inventoryView.title())) {
            Bukkit.getConsoleSender().sendMessage("Nenhum inventário corresponde!");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("Chegamos a algum lugar!");

        int clickedSlot = event.getSlot();
        ItemStack clickedItem = event.getCurrentItem();

        InventoryClickAction inventoryClickAction = this.inventoryManager.getAction(clickedSlot, clickedItem);
        if (inventoryClickAction != null) {
            inventoryClickAction.onClick(event);
        }
    }
}
