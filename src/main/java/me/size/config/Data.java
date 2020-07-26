package me.size.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;


public class Data {

    private final ConfigManager configManager;
    private final FileConfiguration fileConfiguration;
    private final UUID uuid;


    public Data(UUID uuid, ConfigManager configManager) {
        this.uuid = uuid;
        this.configManager = configManager;
        this.fileConfiguration = configManager.getFileConfiguration();
    }


    public int getCoins() {
        if (!fileConfiguration.isSet("coins." + uuid.toString())) {
            return 0;
        }
        int balance = fileConfiguration.getInt("coins." + uuid.toString());
        if (balance < 0) {
            return -1;
        }

        return balance;
    }


    public void setCoins(int balance) {

        this.fileConfiguration.set("coins." + uuid.toString(), balance);
        this.configManager.save();

    }


    public void setName(UUID uuid) {

        this.fileConfiguration.set("coins." + uuid.toString(), Bukkit.getOfflinePlayer(uuid).getName());
        this.configManager.save();
    }

}
