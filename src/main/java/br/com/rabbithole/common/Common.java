package br.com.rabbithole.common;

import br.com.rabbithole.common.core.Messages;

/**
 * Classe de acesso da Biblioteca.
 *
 * @author Felipe Ros
 * @since 1.0.0
 * @version 1.0.0
 */
public final class Common {
    private static Messages messages;

    public Common() {
        messages = new Messages();
    }

    public Messages getMessages() {
        return messages;
    }
}
