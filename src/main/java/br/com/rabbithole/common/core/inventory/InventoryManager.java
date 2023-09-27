package br.com.rabbithole.common.core.inventory;

import br.com.rabbithole.common.core.inventory.models.InventoryBase;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private final List<InventoryBase> inventoryRegister;

    public InventoryManager(Plugin plugin) {
        this.inventoryRegister = new ArrayList<>();
        new InventoryListener(plugin, this);
    }

    public void registerInventory(InventoryBase inventory) {
        this.inventoryRegister.add(inventory);
    }

    public List<InventoryBase> getInventoryRegister() {
        return inventoryRegister;
    }

    public boolean isMember(Inventory inventory) {
        for (InventoryBase registeredInventory : this.inventoryRegister) {
            if (registeredInventory.getInventory().equals(inventory)) return true;
        }
        return false;
    }
}
