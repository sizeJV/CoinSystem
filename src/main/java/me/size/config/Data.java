package me.size.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;


public class Data {

    private final ConfigManager configManager;
    private static FileConfiguration fileConfiguration;
    private final String uuid;

    public Data(UUID uuid, ConfigManager configManager) {
        this.uuid = String.valueOf(uuid);
        this.configManager = configManager;
        fileConfiguration = configManager.getFileConfiguration();
    }


    public int getCoins() {
        if (!fileConfiguration.isSet("coins." + uuid)) {
            return 0;
        }
        int balance = fileConfiguration.getInt("coins." + uuid);
        if (balance < 0) {
            return -1;
        }
        return balance;
    }


    public void setCoins(int balance) {
        fileConfiguration.set("coins." + uuid, balance);
        this.configManager.save();
    }
}
