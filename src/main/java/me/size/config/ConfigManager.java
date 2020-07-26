package me.size.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public class ConfigManager {

    private final File configFile;
    private final FileConfiguration fileConfiguration;


    public ConfigManager(String name, JavaPlugin plugin) {
        configFile = new File(plugin.getDataFolder().getPath() + "/" + name + ".yml");
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
    }


    public void save() {
        try {
            fileConfiguration.save(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }
}


