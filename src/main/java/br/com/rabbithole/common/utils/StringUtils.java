package br.com.rabbithole.common.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * Classe de formatação de String's
 *
 * @author Felipe Ros
 * @since 1.0.0
 * @version 1.0.0
 */
public class StringUtils {

    /**
     * Formatação de uma String para Component {@link Component}
     *
     * @param input Mensagem que será formatada.
     * @return Mensagem formatada para Component.
     */
    public static Component format(String input) {
        return MiniMessage.miniMessage().deserialize(input);
    }
}
