package me.size.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class PlayerCoinsChangeEvent extends Event implements Cancellable {

    private final Player player;
    private final Integer amount;

    private boolean bool = false;


    public PlayerCoinsChangeEvent(Player player, int amount) {
        this.player = player;
        this.amount = amount;
    }


    private static final HandlerList HANDLERS = new HandlerList();


    public HandlerList getHandlers() {
        return HANDLERS;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }


    public Player getPlayer() {
        return player;
    }


    public Integer getAmount() {
        return amount;
    }


    @Override
    public boolean isCancelled() {
        return bool;
    }


    @Override
    public void setCancelled(boolean b) {
        this.bool = b;
    }
}
