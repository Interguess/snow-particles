package de.interguess.igsnowparticles.runnables;

import de.interguess.igsnowparticles.configs.SettingsConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleTask extends BukkitRunnable {

    private final JavaPlugin plugin;

    private final SettingsConfig settingsConfig;

    public ParticleTask(JavaPlugin plugin, SettingsConfig settingsConfig) {
        this.plugin = plugin;
        this.settingsConfig = settingsConfig;
    }

    @Override
    public void run() {
        if (!plugin.isEnabled()) {
            this.cancel();

            return;
        }

        if (!settingsConfig.getBoolean("status")) {
            return;
        }

        Particle particle = settingsConfig.getParticle("particle");

        double radius = settingsConfig.getInt("radius");
        double minHeight = settingsConfig.getInt("minHeight");
        double maxHeight = settingsConfig.getInt("maxHeight");

        int count = settingsConfig.getInt("count");

        for (Player player : Bukkit.getOnlinePlayers()) {
            Location playerLocation = player.getLocation();

            if (playerLocation.getWorld() != null) {
                for (int i = 0; i < count; i++) {
                    double randomAngle = Math.random() * 2 * Math.PI;
                    double randomRadius = Math.random() * radius;
                    double x = playerLocation.getX() + randomRadius * Math.cos(randomAngle);
                    double z = playerLocation.getZ() + randomRadius * Math.sin(randomAngle);
                    double randomHeight = Math.random() * (maxHeight - minHeight) + minHeight;

                    player.spawnParticle(particle, x, playerLocation.getY() + randomHeight, z, 1, 0, 0, 0, 0);
                }
            }
        }
    }
}
