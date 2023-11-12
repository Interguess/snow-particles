package de.interguess.igsnowparticles.commands;

import de.interguess.igsnowparticles.IgSnowParticles;
import de.interguess.igsnowparticles.configs.MessagesConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class IgSnowParticlesCommand implements TabExecutor {

    private final IgSnowParticles plugin;

    public IgSnowParticlesCommand(IgSnowParticles plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        MessagesConfig messagesConfig = new MessagesConfig(plugin);

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("igsnowparticles.reload")) {
                try {
                    plugin.reloadConfig();
                    sender.sendMessage(messagesConfig.getMessage("prefix") + messagesConfig.getMessage("reload"));
                } catch (Exception e) {
                    sender.sendMessage(messagesConfig.getMessage("prefix") + messagesConfig.getMessage("reloadError"));
                }
            } else {
                sender.sendMessage(messagesConfig.getMessage("prefix") + messagesConfig.getMessage("no-permission"));
            }
            return true;
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
