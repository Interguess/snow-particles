package de.interguess.igsnowparticles.configs;

import com.google.inject.Inject;
import de.interguess.igsnowparticles.config.Config;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

import javax.inject.Singleton;

@Singleton
public class SettingsConfig extends Config {

    @Inject
    public SettingsConfig(Plugin plugin) {
        super(plugin, "settings.yml");
    }

    public int getInt(String key) {
        return config.getInt(key);
    }

    public Particle getParticle(String key) {
        Particle particle;
        try {
            particle = Particle.valueOf(config.getString(key));
        } catch (IllegalArgumentException e) {
            particle = Particle.END_ROD;
        }
        return particle;
    }

    public boolean getBoolean(String key) {
        return config.getBoolean(key);
    }
}
