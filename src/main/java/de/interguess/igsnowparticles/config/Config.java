package de.interguess.igsnowparticles.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

@Slf4j
@Getter
public class Config {

    private final Plugin plugin;

    private final String name;

    private File configFile;

    public YamlConfiguration config; //for public access to the config

    public Config(Plugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;

        this.initialize();
    }

    private void initialize() {
        configFile = new File(plugin.getDataFolder(), name);

        if (!configFile.exists()) {
            if (plugin.getResource(configFile.getName()) != null) {
                plugin.saveResource(configFile.getName(), false);
            } else {
                try {
                    configFile.createNewFile();
                } catch (Exception e) {
                    log.error(String.format("Failed to create config %s!", name), e);
                }
            }
        }

        load();
    }

    public void load() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            log.error(String.format("Failed to save config %s!", name), e);
        }
    }
}