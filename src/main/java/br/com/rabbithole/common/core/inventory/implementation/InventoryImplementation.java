package br.com.rabbithole.common.core.inventory.implementation;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.models.InventoryBase;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class InventoryImplementation implements InventoryBase, InventoryHolder {
    private final Component inventoryName;
    private final int inventorySize;
    private final Inventory inventory;
    private final Map<Integer, ItemStack> registeredItems;
    private final Map<Integer, InventoryClickAction> registeredActions;

    public InventoryImplementation(Component inventoryName, int inventorySize) {
        this.inventoryName = inventoryName;
        this.inventorySize = inventorySize;
        this.inventory = Bukkit.createInventory(this, inventorySize, inventoryName);
        this.registeredItems = new HashMap<>();
        this.registeredActions = new HashMap<>();
    }

    @Override
    public Component getInventoryName() {
        return this.inventoryName;
    }

    @Override
    public int getInventorySize() {
        return this.inventorySize;
    }

    @Override
    public Map<Integer, ItemStack> getRegisteredItems() {
        return this.registeredItems;
    }

    @Override
    public Map<Integer, InventoryClickAction> getRegisteredActions() {
        return this.registeredActions;
    }


    @Override
    public void addItem(int slot, ItemStack item) {
        //TODO: TESTAR SE VALE A PENA UTILIZAR OS 2 OU SÓ MANTER O INVENTORY.SETITEM
        this.registeredItems.put(slot, item);
        this.inventory.setItem(slot, item);
    }

    @Override
    public void addAction(int slot, InventoryClickAction action) {
        this.registeredActions.put(slot, action);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
