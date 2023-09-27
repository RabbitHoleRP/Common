package br.com.rabbithole.common.core.inventory;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.models.InventoryBase;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private final List<InventoryBase> inventoryRegistry;

    public InventoryManager(Plugin plugin) {
        this.inventoryRegistry = new ArrayList<>();
        new InventoryListener(plugin, this);
    }

    public void registerInventory(InventoryBase inventory) {
        this.inventoryRegistry.add(inventory);
    }

    public List<InventoryBase> getInventoryRegistry() {
        return inventoryRegistry;
    }

    public boolean isRegistered(Inventory inventory) {
        for (InventoryBase registeredInventory : this.inventoryRegistry) {
            if (registeredInventory.getInventory().equals(inventory)) return true;
        }
        return false;
    }

    //TODO: REMOVER!
    public boolean hasAction(int slot, ItemStack item) {
        for (InventoryBase registeredInventory : this.inventoryRegistry) {
            ItemStack itemRegistered = null;
            if (registeredInventory.getRegisteredItems().containsKey(slot)) itemRegistered = registeredInventory.getRegisteredItems().get(slot);
            if (registeredInventory.getRegisteredActions().containsKey(slot) && itemRegistered != null) return true;
        }
        return false;
    }

    public InventoryClickAction getAction(int slot, ItemStack item) {
        for (InventoryBase registeredInventory : this.inventoryRegistry) {
            ItemStack registeredItem = null;
            if (registeredInventory.getRegisteredItems().containsKey(slot)) registeredItem = registeredInventory.getRegisteredItems().get(slot);
            if (registeredInventory.getRegisteredActions().containsKey(slot) && registeredItem != null)
                return registeredInventory.getRegisteredActions().get(slot);
        }
        return null;
    }
}
