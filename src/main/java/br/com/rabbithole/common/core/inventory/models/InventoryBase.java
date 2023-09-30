package br.com.rabbithole.common.core.inventory.models;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.actions.InventoryCloseAction;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public interface InventoryBase extends InventoryHolder {
    Component getInventoryName();

    int getInventorySize();

    Map<Integer, ItemStack> getRegisteredItems();

    Map<Integer, InventoryClickAction> getRegisteredLeftClickActions();

    Map<Integer, InventoryClickAction> getRegisteredRightClickActions();

    List<InventoryCloseAction> getRegisteredCloseActions();

    void addItem(int slot, ItemStack item);

    void addLeftClickAction(int slot, InventoryClickAction action);

    void addRightClickAction(int slot, InventoryClickAction action);

    void addCloseAction(InventoryCloseAction event);
}
