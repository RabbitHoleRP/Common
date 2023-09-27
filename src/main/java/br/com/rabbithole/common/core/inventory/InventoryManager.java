package br.com.rabbithole.common.core.inventory;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.models.InventoryBase;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManager {
    private final Map<Component, InventoryBase> inventoryRegistry;

    public InventoryManager(Plugin plugin) {
        this.inventoryRegistry = new HashMap<>();
        new InventoryListener(plugin, this);
    }

    public void registerInventory(Component inventoryName, InventoryBase inventory) {
        this.inventoryRegistry.put(inventoryName, inventory);
    }

    public Map<Component, InventoryBase> getInventoryRegistry() {
        return this.inventoryRegistry;
    }

    public boolean isRegistered(Component inventoryName) {
        /*
        for (InventoryBase registeredInventory : this.inventoryRegistry) {
            if (registeredInventory.getInventoryName().equals(inventoryName)) return true;
        }
         */
        return this.inventoryRegistry.containsKey(inventoryName);
    }

    //TODO: REMOVER!
    /*
    public boolean hasAction(int slot, ItemStack item) {
        for (InventoryBase registeredInventory : this.inventoryRegistry) {
            ItemStack itemRegistered = null;
            if (registeredInventory.getRegisteredItems().containsKey(slot)) itemRegistered = registeredInventory.getRegisteredItems().get(slot);
            if (registeredInventory.getRegisteredActions().containsKey(slot) && itemRegistered != null) return true;
        }
        return false;
    }
     */

    public InventoryClickAction getAction(Component inventoryName, int slot, ItemStack item) {
        /*
        for (InventoryBase registeredInventory : this.inventoryRegistry) {
            ItemStack registeredItem = null;
            if (registeredInventory.getRegisteredItems().containsKey(slot)) registeredItem = registeredInventory.getRegisteredItems().get(slot);
            if (registeredInventory.getRegisteredActions().containsKey(slot) && registeredItem != null)
                return registeredInventory.getRegisteredActions().get(slot);
        }
         */
        InventoryBase registeredInventory = this.inventoryRegistry.get(inventoryName);
        if (registeredInventory == null) return null;

        ItemStack registeredItem = null;
        if (registeredInventory.getRegisteredItems().containsKey(slot)) registeredItem = registeredInventory.getRegisteredItems().get(slot);
        if (registeredInventory.getRegisteredActions().containsKey(slot) && registeredItem != null)
            return registeredInventory.getRegisteredActions().get(slot);

        return null;
    }
}
