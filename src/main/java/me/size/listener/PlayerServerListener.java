package me.size.listener;

import me.size.CoinSystem;
import me.size.util.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class PlayerServerListener implements Listener {

    /**
     * @param event: Checks if Player is in the database
     * if not he will get Initialized else receive his Coins.
     */

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID player = event.getPlayer().getUniqueId();

        Bukkit.getScheduler().runTaskAsynchronously(CoinSystem.instance, () -> {
            try (Connection connection = CoinSystem.instance.getDatabase().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `coins_data` WHERE `uuid`=?;")) {
                    statement.setString(1, String.valueOf(player));
                    ResultSet rs = statement.executeQuery();

                    if (!rs.next()) {
                        // INITIALIZES PLAYER
                        CoinsAPI.initPlayer(player);
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        });
        CoinsAPI.getDatabaseCoins(player);
    }


    /**
     * @param event: Closes potential data-leaks
     */

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID player = event.getPlayer().getUniqueId();
        CoinsAPI.dropCachedPlayer(player);
    }
}
