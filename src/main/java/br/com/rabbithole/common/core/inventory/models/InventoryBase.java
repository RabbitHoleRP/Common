package br.com.rabbithole.common.core.inventory.models;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface InventoryBase {
    Component getInventoryName();

    int getInventorySize();

    Map<Integer, ItemStack> getRegisteredItems();

    Map<Integer, InventoryClickAction> getRegisteredActions();

    void addItem(int slot, ItemStack item);

    void addAction(int slot, InventoryClickAction action);

    InventoryHolder getInventoryHolder();
}
