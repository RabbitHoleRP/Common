package br.com.rabbithole.common.core.inventory.models;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.buttons.DisplayButton;
import br.com.rabbithole.common.core.inventory.buttons.SystemButton;
import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

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

    List<DisplayButton> getRegisteredItems();

    List<SystemButton> getCustomButtons();

    String getClickedItemID(int slot);

    InventoryClickAction getDefaultLeftClickAction();

    InventoryClickAction getDefaultRightClickAction();

    Map<Integer, InventoryClickAction> getCustomButtonLeftClickActions();

    Map<Integer, InventoryClickAction> getCustomButtonRightClickActions();

    void addCustomButton(SystemButton button);

    void setDefaultLeftClickAction(InventoryClickAction action);

    void setDefaultRightClickAction(InventoryClickAction action);

    void addCustomLeftClickAction(int slot, InventoryClickAction action);

    void addCustomRightClickAction(int slot, InventoryClickAction action);

    void executeForwardAction(InventoryClickEvent event);

    void executeBackAction(InventoryClickEvent event);

    void setDisplayItems();
}
