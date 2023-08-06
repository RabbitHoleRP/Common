package br.com.rabbithole.common.core;

import br.com.rabbithole.common.utils.StringUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Messages {
    public void sendError(@NotNull Player player, @NotNull String errorMessage) {
        player.sendMessage(StringUtils.format("<red>" + errorMessage));
    }

    public void sendWarn(@NotNull Player player, @NotNull String warnMessage) {
        player.sendMessage(StringUtils.format("<yellow>" + warnMessage));
    }

    public void sendSuccess(@NotNull Player player, @NotNull String successMessage) {
        player.sendMessage(StringUtils.format("<green>" + successMessage));
    }
}
