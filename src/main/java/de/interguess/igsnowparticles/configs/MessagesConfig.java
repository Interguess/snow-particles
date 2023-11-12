package de.interguess.igsnowparticles.configs;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.interguess.igsnowparticles.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

@Singleton
public class MessagesConfig extends Config {

    @Inject
    public MessagesConfig(Plugin plugin) {
        super(plugin, "messages.yml");
    }

    public String getMessage(String key) {
        String message = config.getString(key);

        return message != null ? ChatColor.translateAlternateColorCodes('&', message) : key;
    }
}