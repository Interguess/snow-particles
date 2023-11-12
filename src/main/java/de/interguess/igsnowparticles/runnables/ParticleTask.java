package de.interguess.igsnowparticles.runnables;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.interguess.igsnowparticles.configs.SettingsConfig;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

@Singleton
public class ParticleTask extends BukkitRunnable {

    private final SettingsConfig settingsConfig;

    @Inject
    public ParticleTask(SettingsConfig settingsConfig) {
        this.settingsConfig = settingsConfig;
    }

    @Override
    public void run() {
        if (!settingsConfig.isEnabled()) {
            return;
        }

        double radius = settingsConfig.getRadius();

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.spawnParticle(settingsConfig.getParticle(), player.getLocation(), settingsConfig.getCount(), radius, radius, radius);
        });
    }
}
