package me.size.commands;

import me.size.events.PlayerCoinsChangeEvent;
import me.size.util.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }

        Player player = (Player) commandSender;

        if (strings.length == 2) {
            if (!player.hasPermission("coins.set")) {
                return true;
            }
            Player target = Bukkit.getPlayer(strings[0]);
            if (target == null) {
                player.sendMessage("§7[§6Coins§7] §cThis Player isn't online.");
                return true;
            }
            Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(target, Integer.parseInt(strings[1])));
            player.sendMessage(
                    "§7[§6Coins§7] §e" + target.getName() + "§a now has:§e " + CoinsAPI.getCoins(target.getUniqueId()) + "§a Coins");
            return true;
        }
        else {
            player.sendMessage(
                    "§7[§6Coins§7] §cUsage: §e/setcoins <player> <amount>");
            return true;
        }
    }
}
