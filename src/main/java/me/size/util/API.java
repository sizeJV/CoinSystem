package me.size.util;

import me.size.CoinSystem;
import me.size.config.Data;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Objects;
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
        Data data = new Data(uuid, CoinSystem.instance.getConfigManager());
        data.setCoins(amount);
    }


    /**
     * <API>
     *
     * @param uuid: Player to receive the Coins from
     * </API>
     */
    public static int getCoins(UUID uuid) {
        Data data = new Data(uuid, CoinSystem.instance.getConfigManager());
        return data.getCoins();
    }


    /**
     * <API>
     *
     * @param name: searches for Player Online if not online searches offline
     * </API>
     */
    public static Player getPlayer(String name) {
        Player player = null;

        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);


        for (int i = players.length - 1; i >= 0; i--) {
            if (Objects.equals(players[i].getName(), name)) {
                player = players[i].getPlayer();
                break;
            }
        }

        if (player == null) {
            return player;
        }

        OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();

        for (int i = offlinePlayers.length - 1; i >= 0; i--) {
            if (Objects.equals(offlinePlayers[i].getName(), name)) {
                player = offlinePlayers[i].getPlayer();
                break;
            }
        }
        return player;
    }
}
