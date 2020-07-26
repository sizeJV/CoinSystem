package me.size.util;

import me.size.CoinSystem;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class CachingLoad {

    public CachingLoad() {
        load();
    }


    public void load() {
        OfflinePlayer[] players = Bukkit.getOfflinePlayers();

        for (int i = players.length - 1; i >= 0; i--) {
            UUID player = players[i].getUniqueId();

            Bukkit.getScheduler().runTaskAsynchronously(CoinSystem.instance, () -> {
                try (Connection connection = CoinSystem.instance.getDatabase().getConnection()) {
                    try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `coins_data` WHERE `uuid`=?;")) {
                        statement.setString(1, String.valueOf(player));
                        ResultSet rs = statement.executeQuery();

                        if (!rs.next()) {
                            // INITIALIZES PLAYER
                            CoinsHandler.initPlayer(player);
                        }
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            CoinsHandler.getDatabaseCoins(player);
        }
    }
}
