package me.size.listener;

import me.size.events.PlayerCoinsChangeEvent;
import me.size.util.API;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;


public class PlayerCoinsChangeListener implements Listener {

    @EventHandler
    public void onPlayerCoinsChange(PlayerCoinsChangeEvent event) {
        UUID player = event.getPlayer().getUniqueId();
        API.setCoins(player, event.getAmount());
    }
}
