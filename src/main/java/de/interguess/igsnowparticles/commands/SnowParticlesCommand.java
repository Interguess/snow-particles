package de.interguess.igsnowparticles.commands;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.interguess.igsnowparticles.configs.MessagesConfig;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Slf4j
@Singleton
public class SnowParticlesCommand implements TabExecutor {

    private final Plugin plugin;

    private final MessagesConfig messagesConfig;

    @Inject
    public SnowParticlesCommand(Plugin plugin, MessagesConfig messagesConfig) {
        this.plugin = plugin;
        this.messagesConfig = messagesConfig;

        register();
    }

    private void register() {
        PluginCommand command = plugin.getServer().getPluginCommand("igsnowparticles");

        assert command != null; // This should never be null

        command.setExecutor(this);
        command.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1 || !args[0].equalsIgnoreCase("reload")) {
            return false;
        }

        try {
            log.info("[IgSnowParticles] Reloading plugin...");

            Bukkit.getPluginManager().disablePlugin(plugin);
            Bukkit.getPluginManager().enablePlugin(plugin);

            sender.sendMessage(messagesConfig.getMessage("prefix") + messagesConfig.getMessage("reload"));
        } catch (Exception e) {
            sender.sendMessage(messagesConfig.getMessage("prefix") + messagesConfig.getMessage("reload-error"));
        }

        return true;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("reload");
        }

        return null;
    }
}
