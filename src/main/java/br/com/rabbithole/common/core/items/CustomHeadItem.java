package br.com.rabbithole.common.core.items;

import br.com.rabbithole.common.utils.SkullUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomHeadItem {
    public ItemStack createCustomHead(@NotNull Component displayName, @NotNull String skinURL) {
        ItemStack itemStack = SkullUtils.itemFromUrl(skinURL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack createCustomHead(@NotNull Component displayName, @NotNull List<Component> lore, @NotNull String skinURL) {
        ItemStack itemStack = SkullUtils.itemFromUrl(skinURL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(displayName);
        itemMeta.lore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
