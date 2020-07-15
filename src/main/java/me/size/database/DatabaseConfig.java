package me.size.database;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class DatabaseConfig extends AbstractConfig {

    public static final String MYSQL_USERNAME = "username";
    public static final String MYSQL_PASSWORD = "password";
    public static final String MYSQL_URL = "url";
    public static final String MYSQL_DATABASE = "database";
    public static final String MYSQL_POOLSIZE = "poolsize";


    public DatabaseConfig(JavaPlugin plugin) {
        super(plugin, "mysql");
    }


    @Override
    protected void saveDefaultConfig(FileConfiguration config) {
        config.set(MYSQL_USERNAME, "username");
        config.set(MYSQL_PASSWORD, "password");
        config.set(MYSQL_URL, "url");
        config.set(MYSQL_DATABASE, "database");
        config.set(MYSQL_POOLSIZE, 3);
    }


    @Override
    public String getString(String key) {
        return getFileConfiguration().getString(key);
    }


    public String getDatabaseUrl() {
        return getFileConfiguration().getString(MYSQL_URL);
    }


    public String getDatabase() {
        return getFileConfiguration().getString(MYSQL_DATABASE);
    }


    public String getUsername() {
        return getFileConfiguration().getString(MYSQL_USERNAME);
    }


    public String getPassword() {
        return getFileConfiguration().getString(MYSQL_PASSWORD);
    }


    public int getPoolSize() {
        return getFileConfiguration().getInt(MYSQL_POOLSIZE);
    }
}
