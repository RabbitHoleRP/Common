package br.com.rabbithole.common.core.inventory.implementation;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.buttons.DisplayButton;
import br.com.rabbithole.common.core.inventory.buttons.SystemButton;
import br.com.rabbithole.common.core.inventory.models.PaginationBase;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PaginationImplementation implements PaginationBase {
    private final Component inventoryName;
    private final int inventorySize;
    private final int maxItemsPerPage;
    private final int totalPages;
    private int actualPage;
    private final Inventory inventory;
    private final int[] itemsPattern;
    private final SystemButton backButton;
    private final SystemButton forwardButton;
    private final List<DisplayButton> registeredItems;
    private final List<SystemButton> registeredCustomButtons;
    private final Map<Integer, DisplayButton> displayedItems;
    private InventoryClickAction defaultLeftClickAction;
    private InventoryClickAction defaultRightClickAction;
    private final Map<Integer, InventoryClickAction> customButtonLeftClickAction;
    private final Map<Integer, InventoryClickAction> customButtonRightClickAction;

    public PaginationImplementation(Component inventoryName, int inventorySize, int maxItemsPerPage, int[] itemsPattern, SystemButton backButton, SystemButton forwardButton, List<DisplayButton> registeredItems) {
        this.inventoryName = inventoryName;
        this.inventorySize = inventorySize;
        this.maxItemsPerPage = maxItemsPerPage;
        this.totalPages = (int) Math.ceil((double) registeredItems.size() / maxItemsPerPage);
        this.actualPage = 0;
        this.inventory = Bukkit.createInventory(this, inventorySize, inventoryName);
        this.itemsPattern = itemsPattern;
        this.backButton = backButton;
        this.forwardButton = forwardButton;
        this.registeredItems = registeredItems;
        this.registeredCustomButtons = new ArrayList<>();
        this.displayedItems = new HashMap<>();
        this.customButtonLeftClickAction = new HashMap<>();
        this.customButtonRightClickAction = new HashMap<>();
        this.setDisplayItems();
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
    public int getMaxItemsPerPage() {
        return this.maxItemsPerPage;
    }

    @Override
    public int getTotalPages() {
        return this.totalPages;
    }

    @Override
    public int getActualPage() {
        return this.actualPage;
    }

    @Override
    public int[] getItemsPattern() {
        return this.itemsPattern;
    }

    @Override
    public int getBackButtonSlot() {
        return this.backButton.slot();
    }

    @Override
    public int getForwardButtonSlot() {
        return this.forwardButton.slot();
    }

    @Override
    public List<DisplayButton> getRegisteredItems() {
        return this.registeredItems;
    }

    @Override
    public List<SystemButton> getCustomButtons() {
        return this.registeredCustomButtons;
    }

    @Override
    public String getClickedItemID(int slot) {
        return this.displayedItems.get(slot).id();
    }

    @Override
    public InventoryClickAction getDefaultLeftClickAction() {
        return this.defaultLeftClickAction;
    }

    @Override
    public InventoryClickAction getDefaultRightClickAction() {
        return this.defaultRightClickAction;
    }

    @Override
    public Map<Integer, InventoryClickAction> getCustomButtonLeftClickActions() {
        return this.customButtonLeftClickAction;
    }

    @Override
    public Map<Integer, InventoryClickAction> getCustomButtonRightClickActions() {
        return this.customButtonRightClickAction;
    }

    @Override
    public void addCustomButton(SystemButton button) {
        if (Arrays.stream(this.itemsPattern).noneMatch(x -> x == button.slot()) && button.slot() != this.backButton.slot() && button.slot() != this.forwardButton.slot()) {
            this.registeredCustomButtons.add(button);
            this.inventory.setItem(button.slot(), button.icon());
        }
    }

    @Override
    public void setDefaultLeftClickAction(InventoryClickAction action) {
        this.defaultLeftClickAction = action;
    }

    @Override
    public void setDefaultRightClickAction(InventoryClickAction action) {
        this.defaultRightClickAction = action;
    }

    @Override
    public void addCustomLeftClickAction(int slot, InventoryClickAction action) {
        if (Arrays.stream(this.itemsPattern).noneMatch(x -> x == slot) && slot != this.backButton.slot() && slot != this.forwardButton.slot())
            this.customButtonLeftClickAction.put(slot, action);
    }

    @Override
    public void addCustomRightClickAction(int slot, InventoryClickAction action) {
        if (Arrays.stream(this.itemsPattern).noneMatch(x -> x == slot) && slot != this.backButton.slot() && slot != this.forwardButton.slot())
            this.customButtonRightClickAction.put(slot, action);
    }

    @Override
    public void executeForwardAction(InventoryClickEvent event) {
        InventoryClickAction forwardAction = (inventoryClickEvent -> {
            this.actualPage += 1;
            if (this.actualPage == this.totalPages) this.actualPage -= 1;
            this.updateView();
        });
        forwardAction.onClick(event);
    }

    @Override
    public void executeBackAction(InventoryClickEvent event) {
        InventoryClickAction backAction = (inventoryClickEvent -> {
            this.actualPage -= 1;
            if (this.actualPage < 0) this.actualPage = 0;
            this.updateView();
        });
        backAction.onClick(event);
    }

    @Override
    public void setDisplayItems() {
        this.displayedItems.clear();

        if (this.actualPage != 0) this.inventory.setItem(this.backButton.slot(), this.backButton.icon());
        if (this.actualPage < this.totalPages - 1) this.inventory.setItem(this.forwardButton.slot(), this.forwardButton.icon());

        int indexBase = this.actualPage * this.maxItemsPerPage;
        for (int pattern : this.itemsPattern) {
            if (indexBase == this.registeredItems.size()) return;
            this.inventory.setItem(pattern, this.registeredItems.get(indexBase).icon());
            this.displayedItems.put(pattern, this.registeredItems.get(indexBase));
            indexBase++;
        }

        for (SystemButton customButton : this.registeredCustomButtons) {
            this.inventory.setItem(customButton.slot(), customButton.icon());
        }
    }

    public void updateView() {
        this.inventory.clear();
        this.setDisplayItems();
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
