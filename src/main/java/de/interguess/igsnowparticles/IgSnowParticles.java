package de.interguess.igsnowparticles;

import de.interguess.igsnowparticles.configs.MessagesConfig;
import de.interguess.igsnowparticles.configs.SettingsConfig;
import de.interguess.igsnowparticles.commands.IgSnowParticlesCommand;
import de.interguess.igsnowparticles.runnables.ParticleTask;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class IgSnowParticles extends JavaPlugin {

    private ParticleTask particleTask;

    @Override
    public void onEnable() {
        getLogger().info("IgSnowParticles has been enabled!");

        SettingsConfig settingsConfig = new SettingsConfig(this);
        MessagesConfig messagesConfig = new MessagesConfig(this);

        PluginCommand snowParticlesCommand = getCommand("igsnowparticles");

        assert snowParticlesCommand != null;

        snowParticlesCommand.setExecutor(new IgSnowParticlesCommand(this));
        snowParticlesCommand.setTabCompleter(new IgSnowParticlesCommand(this));

        particleTask = new ParticleTask(this, settingsConfig);
        particleTask.runTaskTimer(this, 0, settingsConfig.getInt("interval"));
        particleTask.run();
    }

    @Override
    public void onDisable() {
        if (particleTask != null) {
            particleTask.cancel();
        }
    }
}
