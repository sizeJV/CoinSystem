package me.size.commands;

import me.size.events.PlayerCoinsChangeEvent;
import me.size.util.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {

            if (strings.length == 2) {
                Player target = API.getPlayer(strings[0]);
                if (target == null) {
                    Bukkit.getConsoleSender().sendMessage("§7[§6Coins§7] §cPlayer not found!");
                    return true;
                }
                Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(target, Integer.parseInt(strings[1])));
                Bukkit.getConsoleSender().sendMessage(
                        "§7[§6Coins§7] §e" + target.getName() + "§a now has:§e " + API.getCoins(target.getUniqueId()) + "§a Coins");
            }
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length == 2) {
            if (!player.hasPermission("coins.set")) {
                player.sendMessage("§7[§6Coins§7] §cYou are lacking permission");
                return true;
            }
            Player target = API.getPlayer(strings[0]);
            if (target == null) {
                player.sendMessage("§7[§6Coins§7] §cPlayer not found.");
                return true;
            }
            Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(target, Integer.parseInt(strings[1])));
            player.sendMessage(
                    "§7[§6Coins§7] §e" + target.getName() + "§a now has:§e " + API.getCoins(target.getUniqueId()) + "§a Coins");
        }
        else {
            player.sendMessage(
                    "§7[§6Coins§7] §cUsage: §e/setcoins <player> <amount>");
        }
        return true;
    }
}
