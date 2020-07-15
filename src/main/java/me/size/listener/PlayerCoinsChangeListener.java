package me.size.listener;

import me.size.events.PlayerCoinsChangeEvent;
import me.size.util.CoinsAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;


public class PlayerCoinsChangeListener implements Listener {

    @EventHandler
    public void onPlayerCoinsChange(PlayerCoinsChangeEvent event) {
        UUID player = event.getPlayer().getUniqueId();

        CoinsAPI.setCoins(player, event.getAmount());
    }
}
