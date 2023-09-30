package br.com.rabbithole.common.core.inventory.models;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.actions.InventoryCloseAction;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public interface PaginationBase extends InventoryHolder {
    Component getInventoryName();

    int getInventorySize();

    int getMaxItemsPerPage();

    int getTotalPages();

    int getActualPage();

    int[] getItemsPattern();

    int getBackButtonSlot();

    int getForwardButtonSlot();

    List<ItemStack> getRegisteredItems();

    Map<Integer, ItemStack> getRegisteredExtraItems();

    InventoryClickAction getDefaultLeftClickAction();

    InventoryClickAction getDefaultRightClickAction();

    InventoryCloseAction getDefaultCloseAction();

    Map<Integer, InventoryClickAction> getRegisteredExtraLeftActions();

    Map<Integer, InventoryClickAction> getRegisteredExtraRightActions();

    void addExtraItem(int slot, ItemStack item);

    void setDefaultLeftClickAction(InventoryClickAction action);

    void setDefaultRightClickAction(InventoryClickAction action);

    void setDefaultCloseAction(InventoryCloseAction action);

    void forwardButtonAction(Player player);

    void backButtonAction(Player player);

    void setDisplayItems();
}
