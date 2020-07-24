package me.size.listener;

import me.size.events.PlayerPayCoinsEvent;
import me.size.util.API;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;


public class PlayerPayCoinsListener implements Listener {

    @EventHandler
    public void onPlayerPayCoins(PlayerPayCoinsEvent event) {
        UUID source = event.getSource().getUniqueId();
        UUID target = event.getTarget().getUniqueId();

        int targetSum = API.getCoins(target) + event.getAmount();
        int sourceSum = API.getCoins(source) - event.getAmount();

        API.setCoins(target, targetSum);
        API.setCoins(source, sourceSum);
    }
}
