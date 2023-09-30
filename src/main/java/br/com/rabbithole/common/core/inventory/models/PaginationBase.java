package br.com.rabbithole.common.core.inventory.models;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.actions.InventoryCloseAction;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

//TODO: ADICIONAR MÉTODO PARA EXTRA ITEMS ACTION!
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

    InventoryClickAction getForwardClickAction();

    InventoryClickAction getBackClickAction();

    InventoryCloseAction getDefaultCloseAction();

    Map<Integer, InventoryClickAction> getRegisteredExtraLeftActions();

    Map<Integer, InventoryClickAction> getRegisteredExtraRightActions();

    void addExtraItem(int slot, ItemStack item);

    void setForwardIcon(ItemStack item);

    void setBackIcon(ItemStack item);

    void setDefaultLeftClickAction(InventoryClickAction action);

    void setDefaultRightClickAction(InventoryClickAction action);

    void setDefaultCloseAction(InventoryCloseAction action);

    void setForwardButtonAction(Player player, InventoryClickEvent event);

    void setBackButtonAction(Player player, InventoryClickEvent event);

    void setDisplayItems();
}
