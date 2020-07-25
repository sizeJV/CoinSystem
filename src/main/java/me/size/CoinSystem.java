package me.size;

import me.size.commands.AddCommand;
import me.size.commands.CoinsCommand;
import me.size.commands.PayCommand;
import me.size.commands.RemoveCommand;
import me.size.commands.SetCommand;
import me.size.database.Database;
import me.size.listener.PlayerCoinsChangeListener;
import me.size.listener.PlayerPayCoinsListener;
import me.size.util.CachingDeload;
import me.size.util.CachingLoad;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;


public class CoinSystem extends JavaPlugin {

    public static CoinSystem instance;

    private Database database;

    @Override
    public void onEnable() {
        instance = this;


        database = new Database(instance);
        try {
            database.connect();
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        new CachingLoad();

        Arrays.asList(
                new PlayerCoinsChangeListener(),
                new PlayerPayCoinsListener()
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
        new CachingDeload(this);
        Bukkit.getConsoleSender().sendMessage("[Coins-System] successfully disabled plugin");
    }


    public Database getDatabase() {
        return database;
    }
}
