package br.com.rabbithole.common.core.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.logging.Level;

/**
 * Classe para criação de Arquivos YML.
 *
 * @author Felipe Ros
 * @since 1.1.19
 * @version 1.0.0
 */
public class Config {
    private final String fileName;
    private final Plugin plugin;
    private File file;
    private FileConfiguration config;

    public Config(String fileName, Plugin plugin) {
        this.fileName = fileName;
        this.plugin = plugin;
        setupConfigFile();
    }

    private void setupConfigFile() {
        if (this.plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();

        this.file = new File(plugin.getDataFolder(), fileName);

        if (!this.file.exists()) {
            try {
                this.plugin.saveResource(fileName, false);
            } catch (Exception exception) {
                this.plugin.getLogger().log(Level.SEVERE, "Erro ao criar arquivo " + fileName);
                exception.printStackTrace(System.err);
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (Exception exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Erro ao salvar informações no arquivo " + fileName);
            exception.printStackTrace(System.err);
        }
    }
}
