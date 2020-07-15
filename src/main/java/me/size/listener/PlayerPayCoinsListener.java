package me.size.listener;

import me.size.events.PlayerPayCoinsEvent;
import me.size.util.CoinsAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;


public class PlayerPayCoinsListener implements Listener {

    @EventHandler
    public void onPlayerPayCoins(PlayerPayCoinsEvent event) {
        UUID source = event.getSource().getUniqueId();
        UUID target = event.getTarget().getUniqueId();

        int targetSum = CoinsAPI.getCoins(target) + event.getAmount();
        int sourceSum = CoinsAPI.getCoins(source) + event.getAmount();

        CoinsAPI.setCoins(target, targetSum);
        CoinsAPI.setCoins(source, sourceSum);
    }
}
