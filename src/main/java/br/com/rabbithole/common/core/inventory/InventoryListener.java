package br.com.rabbithole.common.core.inventory;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.implementation.InventoryImplementation;
import br.com.rabbithole.common.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

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
        InventoryView inventoryView = event.getView();
        Inventory inventory = event.getClickedInventory();

        if (!this.inventoryManager.isRegistered(inventoryView.title())) return;

        InventoryImplementation inventoryImplementation = (InventoryImplementation) Objects.requireNonNull(inventory).getHolder();

        Bukkit.getConsoleSender().sendMessage(inventoryImplementation.getInventoryName());

        /*
        InventoryView inventoryView = event.getView();

        if (!this.inventoryManager.isRegistered(inventoryView.title())) return;

        event.setCancelled(true);

        int clickedSlot = event.getSlot();
        ItemStack clickedItem = event.getCurrentItem();

        InventoryClickAction inventoryClickAction = this.inventoryManager.getAction(inventoryView.title(), clickedSlot, clickedItem);
        if (inventoryClickAction != null) {
            inventoryClickAction.onClick(event);
        }
         */
    }
}
