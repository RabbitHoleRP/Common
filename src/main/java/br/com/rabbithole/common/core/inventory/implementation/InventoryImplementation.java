package br.com.rabbithole.common.core.inventory.implementation;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.actions.InventoryCloseAction;
import br.com.rabbithole.common.core.inventory.models.InventoryBase;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryImplementation implements InventoryBase {
    private final Component inventoryName;
    private final int inventorySize;
    private final Inventory inventory;
    private final Map<Integer, ItemStack> registeredItems;
    private final Map<Integer, InventoryClickAction> registeredLeftClickActions;
    private final Map<Integer, InventoryClickAction> registeredRightClickActions;
    private final List<InventoryCloseAction> registerCloseActions;

    public InventoryImplementation(Component inventoryName, int inventorySize) {
        this.inventoryName = inventoryName;
        this.inventorySize = inventorySize;
        this.inventory = Bukkit.createInventory(this, this.inventorySize, this.inventoryName);
        this.registeredItems = new HashMap<>();
        this.registeredLeftClickActions = new HashMap<>();
        this.registeredRightClickActions = new HashMap<>();
        this.registerCloseActions = new ArrayList<>();
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
    public Map<Integer, InventoryClickAction> getRegisteredLeftClickActions() {
        return this.registeredLeftClickActions;
    }

    @Override
    public Map<Integer, InventoryClickAction> getRegisteredRightClickActions() {
        return this.registeredRightClickActions;
    }

    @Override
    public List<InventoryCloseAction> getRegisteredCloseActions() {
        return this.registerCloseActions;
    }

    @Override
    public void addItem(int slot, ItemStack item) {
        this.registeredItems.put(slot, item);
        this.inventory.setItem(slot, item);
    }

    @Override
    public void addLeftClickAction(int slot, InventoryClickAction action) {
        this.registeredLeftClickActions.put(slot, action);
    }

    @Override
    public void addRightClickAction(int slot, InventoryClickAction action) {
        this.registeredRightClickActions.put(slot, action);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void addCloseAction(InventoryCloseAction event) {
        this.registerCloseActions.add(event);
    }
}
