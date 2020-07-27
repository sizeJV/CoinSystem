package me.size;

import me.size.commands.AddCommand;
import me.size.commands.CoinsCommand;
import me.size.commands.PayCommand;
import me.size.commands.RemoveCommand;
import me.size.commands.SetCommand;
import me.size.config.ConfigManager;
import me.size.listener.JoinQuitListener;
import me.size.listener.PlayerCoinsChangeListener;
import me.size.listener.PlayerPayCoinsListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;


public class CoinSystem extends JavaPlugin {

    public static CoinSystem instance;

    private ConfigManager configManager;


    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager("data", this);

        Arrays.asList(
                new PlayerCoinsChangeListener(),
                new PlayerPayCoinsListener(),
                new JoinQuitListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        Objects.requireNonNull(this.getCommand("addcoins")).setExecutor(new AddCommand());
        Objects.requireNonNull(this.getCommand("coins")).setExecutor(new CoinsCommand());
        Objects.requireNonNull(this.getCommand("pay")).setExecutor(new PayCommand());
        Objects.requireNonNull(this.getCommand("setcoins")).setExecutor(new SetCommand());
        Objects.requireNonNull(this.getCommand("removecoins")).setExecutor(new RemoveCommand());

        Bukkit.getConsoleSender().sendMessage("[Coins-System] successfully enabled plugin");
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[Coins-System] successfully disabled plugin");
    }


    public ConfigManager getConfigManager() {
        return configManager;
    }
}
