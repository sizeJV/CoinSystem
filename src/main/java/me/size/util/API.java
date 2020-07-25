package me.size.util;

import me.size.CoinSystem;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


/**
 * <API>
 * Only use API related functions when using this API
 * Methods to be used are tagged with the API brackets!
 * </API>
 */

public class API {

    /**
     * <API>
     *
     * @param uuid: Player the Coins will get set to
     * @param amount: Amount the Player will get set
     * </API>
     */

    public static void setCoins(UUID uuid, Integer amount) {
        CoinsHandler.getCachedValues().put(uuid, amount);
    }


    /**
     * <API>
     *
     * @param uuid: Player to receive the Coins from
     * </API>
     */

    public static int getCoins(UUID uuid) {
        if (!CoinsHandler.getCachedValues().containsKey(uuid)) {
            return 0;
        }
        return CoinsHandler.getCachedValues().get(uuid);
    }

    private static int playerCoins;

    public static int getCoinsOffline(UUID uuid) {
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
                    }
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return playerCoins;
    }

}
