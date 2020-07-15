package me.size.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class PlayerPayCoinsEvent extends Event implements Cancellable {

    private final Player source;
    private final Player target;
    private final Integer amount;

    private boolean bool = false;


    public PlayerPayCoinsEvent(Player source, Player target, int amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }


    private static final HandlerList HANDLERS = new HandlerList();


    public HandlerList getHandlers() {
        return HANDLERS;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }


    public Player getSource() {
        return source;
    }


    public Player getTarget() {
        return target;
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