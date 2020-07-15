package me.size.database;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public abstract class AbstractConfig {

    private File configFile;


    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }


    private FileConfiguration fileConfiguration;
    private final JavaPlugin plugin;


    public AbstractConfig(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        createFile(fileName);
        save();
    }


    private void createFile(String fileName) {
        boolean doDefaultSave = false;
        configFile = new File(plugin.getDataFolder() + "/", fileName + ".yml");

        if (!configFile.exists()) {
            doDefaultSave = true;
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        if (doDefaultSave) {
            saveDefaultConfig(getFileConfiguration());
        }
    }


    public void save() {
        try {
            fileConfiguration.save(configFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
    }


    public int getInt(String key) {
        return fileConfiguration.getInt(key);
    }


    public String getString(String key) {
        return fileConfiguration.getString(key);
    }


    public String getConfigPath() {
        return configFile.getPath();
    }


    protected abstract void saveDefaultConfig(FileConfiguration config);
}
