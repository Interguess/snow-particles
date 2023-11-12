package de.interguess.igsnowparticles.configs;

import com.google.inject.Inject;
import de.interguess.igsnowparticles.config.Config;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

import javax.inject.Singleton;

@Getter
@Singleton
public class SettingsConfig extends Config {

    private final boolean enabled;

    private final Particle particle;

    private final int interval;

    private final double radius;

    private final int count;

    @Inject
    public SettingsConfig(Plugin plugin) {
        super(plugin, "settings.yml");

        this.enabled = config.getBoolean("enabled");
        this.particle = getParticleFromConfig();
        this.interval = config.getInt("interval");
        this.radius = config.getDouble("radius");
        this.count = config.getInt("count");
    }

    private Particle getParticleFromConfig() {
        Particle particle = Particle.END_ROD;

        try {
            particle = Particle.valueOf(config.getString("particle"));
        } catch (IllegalArgumentException e) {
            config.set("particle", particle.name());

            save();

            Bukkit.getLogger().warning("The particle '" + config.getString("particle") + "' is not valid. Using default particle.");
        }

        return particle;
    }
}
