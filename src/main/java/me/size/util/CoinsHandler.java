package me.size.util;

import me.size.CoinSystem;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import static me.size.util.API.setCoins;


public class CoinsHandler {

    public CoinsHandler() {
    }


    private static int playerCoins;
    private static final HashMap<UUID, Integer> cachedValues = new HashMap<>();


    public static void getDatabaseCoins(UUID uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(CoinSystem.instance, () -> {
            try (Connection connection = CoinSystem.instance.getDatabase().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT `coins_amount` FROM `coins_data` WHERE `uuid`=(?);")) {
                    statement.setString(1, String.valueOf(uuid));

                    statement.execute();

                    try (ResultSet rs = statement.getResultSet()) {
                        if (!rs.next()) {
                            return;
                        }

                        playerCoins = rs.getInt("coins_amount");
                        setCoins(uuid, playerCoins);
                    }
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }


    public static void setDatabaseCoins(UUID uuid, Integer amount) {
        Bukkit.getScheduler().runTaskAsynchronously(CoinSystem.instance, () -> {
            try (Connection connection = CoinSystem.instance.getDatabase().getConnection()) {
                try (PreparedStatement update = connection.prepareStatement(
                        "UPDATE `coins_data` SET `coins_amount`=? WHERE `uuid`=(?);")) {
                    update.setInt(1, amount);
                    update.setString(2, String.valueOf(uuid));

                    update.executeUpdate();
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }


    /**
     * @param uuid: initializes player that isn't in Database
     */

    public static void initPlayer(UUID uuid) {

        Bukkit.getScheduler().runTaskAsynchronously(CoinSystem.instance, () -> {
            try (Connection connection = CoinSystem.instance.getDatabase().getConnection()) {
                try (PreparedStatement init = connection.prepareStatement(
                        "INSERT INTO `coins_data`(`uuid`, `coins_amount`) VALUES (?,?);")) {
                    init.setString(1, String.valueOf(uuid));
                    init.setInt(2, 0);

                    init.executeUpdate();
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }


    /**
     * @param uuid: Used to remove Player from Cache
     */
    public static void dropCachedPlayer(UUID uuid) {
        setDatabaseCoins(uuid, cachedValues.get(uuid));
    }


    public static HashMap<UUID, Integer> getCachedValues() {
        return cachedValues;
    }
}
