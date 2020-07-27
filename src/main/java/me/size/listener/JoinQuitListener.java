package me.size.listener;

import me.size.CoinSystem;
import me.size.config.Data;
import me.size.util.API;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;


public class JoinQuitListener implements Listener {

    /**
     * @param event: Checks if Player is in the database
     * if not he will get Initialized else receive his Coins.
     */

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID player = event.getPlayer().getUniqueId();
        Data data = new Data(player, CoinSystem.instance.getConfigManager());

        data.setCoins(API.getCoins(player));
    }
}
