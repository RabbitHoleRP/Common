package br.com.rabbithole.common.core.inventory.implementation;

import br.com.rabbithole.common.core.inventory.actions.InventoryClickAction;
import br.com.rabbithole.common.core.inventory.actions.InventoryCloseAction;
import br.com.rabbithole.common.core.inventory.models.PaginationBase;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PaginationImplementation implements PaginationBase {
    private final Component inventoryName;
    private final int inventorySize;
    private final int maxItemsPerPage;
    private final int totalPages;
    private int actualPage;
    private final Inventory inventory;
    private final int[] itemsPattern;
    private final int backButtonSlot;
    private final int forwardButtonSlot;
    private final List<ItemStack> registeredItems;
    private InventoryClickAction defaultLeftClickAction;
    private InventoryClickAction defaultRightClickAction;
    private InventoryCloseAction defaultCloseAction;
    private final Map<Integer, ItemStack> registeredExtraItems;
    private final Map<Integer, InventoryClickAction> registeredExtraLeftClickActions;
    private final Map<Integer, InventoryClickAction> registeredExtraRightClickActions;

    public PaginationImplementation(Component inventoryName, int inventorySize, int maxItemsPerPage, int[] itemsPattern, int backButtonSlot, int forwardButtonSlot, List<ItemStack> registeredItems) {
        this.inventoryName = inventoryName;
        this.inventorySize = inventorySize;
        this.maxItemsPerPage = maxItemsPerPage;
        this.totalPages = (registeredItems.size() / maxItemsPerPage) + 1;
        this.actualPage = 0;
        this.inventory = Bukkit.createInventory(this, inventorySize, inventoryName);
        this.itemsPattern = itemsPattern;
        this.backButtonSlot = backButtonSlot;
        this.forwardButtonSlot = forwardButtonSlot;
        this.registeredItems = registeredItems;
        this.defaultLeftClickAction = null;
        this.defaultRightClickAction = null;
        this.defaultCloseAction = null;
        this.registeredExtraItems = new HashMap<>();
        this.registeredExtraLeftClickActions = new HashMap<>();
        this.registeredExtraRightClickActions = new HashMap<>();
        setDisplayItems();
    }

    private PaginationImplementation(int actualPage) {
        this.inventoryName = getInventoryName();
        this.inventorySize = getInventorySize();
        this.maxItemsPerPage = getMaxItemsPerPage();
        this.totalPages = (getRegisteredItems().size() / getMaxItemsPerPage()) + 1;
        this.actualPage = actualPage;
        this.inventory = Bukkit.createInventory(this, inventorySize, inventoryName);
        this.itemsPattern = getItemsPattern();
        this.backButtonSlot = getBackButtonSlot();
        this.forwardButtonSlot = getForwardButtonSlot();
        this.registeredItems = getRegisteredItems();
        this.defaultLeftClickAction = getDefaultLeftClickAction();
        this.defaultRightClickAction = getDefaultRightClickAction();
        this.defaultCloseAction = getDefaultCloseAction();
        this.registeredExtraItems = getRegisteredExtraItems();
        this.registeredExtraLeftClickActions = getRegisteredExtraLeftActions();
        this.registeredExtraRightClickActions = getRegisteredExtraRightActions();
        setDisplayItems();
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
        return this.backButtonSlot;
    }

    @Override
    public int getForwardButtonSlot() {
        return this.forwardButtonSlot;
    }

    @Override
    public List<ItemStack> getRegisteredItems() {
        return this.registeredItems;
    }

    @Override
    public Map<Integer, ItemStack> getRegisteredExtraItems() {
        return this.registeredExtraItems;
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
    public InventoryCloseAction getDefaultCloseAction() {
        return this.defaultCloseAction;
    }

    @Override
    public Map<Integer, InventoryClickAction> getRegisteredExtraLeftActions() {
        return this.registeredExtraLeftClickActions;
    }

    @Override
    public Map<Integer, InventoryClickAction> getRegisteredExtraRightActions() {
        return this.registeredExtraRightClickActions;
    }

    @Override
    public void addExtraItem(int slot, ItemStack item) {
        this.registeredExtraItems.put(slot, item);
        if (IntStream.of(itemsPattern).noneMatch(x -> x == slot) && this.backButtonSlot != slot && this.forwardButtonSlot != slot)
            this.inventory.setItem(slot, item);
    }

    @Override
    public void setForwardIcon(ItemStack item) {
        this.inventory.setItem(this.forwardButtonSlot, item);
    }

    @Override
    public void setBackIcon(ItemStack item) {
        this.inventory.setItem(this.backButtonSlot, item);
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
    public InventoryClickAction getForwardClickAction() {
        return null;
    }

    @Override
    public InventoryClickAction getBackClickAction() {
        return null;
    }

    @Override
    public void setDefaultCloseAction(InventoryCloseAction action) {
        this.defaultCloseAction = action;
    }

    @Override //TODO: ALTERAR SET PARA EXECUTE!
    public void setForwardButtonAction(Player player, InventoryClickEvent event) {
        InventoryClickAction forwardAction = (clickEvent -> {
            this.actualPage += 1;
            if (this.actualPage > this.totalPages) this.actualPage = totalPages;
            player.openInventory(new PaginationImplementation(actualPage).getInventory());
        });
        forwardAction.onClick(event);
    }

    @Override
    public void setBackButtonAction(Player player, InventoryClickEvent event) {
        InventoryClickAction backAction = (clickEvent -> {
            this.actualPage -= 1;
            if (this.actualPage < 0) this.actualPage = 0;
            player.openInventory(new PaginationImplementation(actualPage).getInventory());
        });
        backAction.onClick(event);
    }

    @Override
    public void setDisplayItems() {
        int indexBase = (this.actualPage * this.maxItemsPerPage) - 1;
        int maxRange = indexBase + this.maxItemsPerPage;
        if (maxRange > this.registeredItems.size() -1) maxRange = this.registeredItems.size() -1;
        List<ItemStack> itemsToDisplay = new ArrayList<>();

        for (int i = indexBase; i < maxRange; i++) {
            itemsToDisplay.add(this.registeredItems.get(i));
        }
        //VOU TER A LISTA TOTAL DE ITENS
        //NÚMERO DE PÁGINAS * MAX_ITEMS_PER_PAGE
        //CASO PÁGINA = 0 ENTÃO T = 0 * 8 = 0;
        //CASO PÁGINA = 1 ENTÃO T = 1 * 8 = 0;
        //T = INDEX DE PESQUISA INICIAL PARA DISPLAY!
        for (int i = 0; i < this.itemsPattern.length; i++) {
            this.inventory.setItem(this.itemsPattern[i], itemsToDisplay.get(i));
        }

        for (Map.Entry<Integer, ItemStack> entry : this.registeredExtraItems.entrySet()) {
            ItemStack anExistentItem = this.inventory.getItem(entry.getKey());
            if (anExistentItem == null) {
                this.inventory.setItem(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
