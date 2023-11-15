package de.interguess.igsnowparticles;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.interguess.igsnowparticles.commands.SnowParticlesCommand;
import de.interguess.igsnowparticles.configs.SettingsConfig;
import de.interguess.igsnowparticles.runnables.ParticleTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class IgSnowParticles extends JavaPlugin {

    private ParticleTask particleTask;

    private Metrics metrics;

    @Override
    public void onEnable() {
        this.getLogger().info("IgSnowParticles has been enabled!");

        Injector injector = Guice.createInjector(new IgSnowParticlesModule());

        SettingsConfig settingsConfig = injector.getInstance(SettingsConfig.class);

        particleTask = injector.getInstance(ParticleTask.class);
        particleTask.runTaskTimerAsynchronously(this, 0, settingsConfig.getInterval());
        particleTask.run();

        metrics = new Metrics(this, 20304);
    }

    @Override
    public void onDisable() {
        if (particleTask != null) {
            particleTask.cancel();
        }

        if (metrics != null) {
            metrics.shutdown();
        }
    }

    class IgSnowParticlesModule extends AbstractModule {

        @Override
        public void configure() {
            bind(Plugin.class).toInstance(IgSnowParticles.this);
            bind(SnowParticlesCommand.class).asEagerSingleton();
        }
    }
}
