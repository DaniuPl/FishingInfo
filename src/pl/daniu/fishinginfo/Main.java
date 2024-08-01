package pl.daniu.fishinginfo;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.daniu.fishinginfo.configuration.messageConfiguration;
import pl.daniu.fishinginfo.listeners.FishingListener;
import pl.daniu.fishinginfo.listeners.ItemHeltListener;

import java.io.File;

public class Main extends JavaPlugin {

    private messageConfiguration messageConfig;

    @Override
    public void onEnable() {
        initConfig();
        Bukkit.getPluginManager().registerEvents(new FishingListener(this, messageConfig), this);
        Bukkit.getPluginManager().registerEvents(new ItemHeltListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    void initConfig(){
        this.messageConfig = ConfigManager.create(messageConfiguration.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer());
            it.withBindFile(new File("plugins/FishingInfo/", "messages.yml"));
            it.saveDefaults();
            it.withRemoveOrphans(true);
            it.load(true);
        });
    }
}
