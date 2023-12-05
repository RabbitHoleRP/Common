package br.com.rabbithole.common.core.serialization;

import br.com.rabbithole.common.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

public class ItemSerialization {
    public static Optional<String> itemStackToBase64(ItemStack item) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(item);
            dataOutput.close();

            return Optional.of(Base64Coder.encodeLines(outputStream.toByteArray()));
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red>Erro ao serializar um Item!"));
            exception.printStackTrace(System.err);
            return Optional.empty();
        }
    }

    public static Optional<ItemStack> base64ToItemStack(String base64) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(base64));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            ItemStack item = (ItemStack) dataInput.readObject();
            dataInput.close();

            return Optional.of(item);
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red>Erro ao deserializar um Item!"));
            exception.printStackTrace(System.err);
            return Optional.empty();
        }
    }
}
