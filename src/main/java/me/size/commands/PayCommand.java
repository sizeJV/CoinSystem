package me.size.commands;

import me.size.events.PlayerPayCoinsEvent;
import me.size.util.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }

        Player player = (Player) commandSender;

        if (strings.length == 2) {
            Player target = API.getPlayer(strings[0]);
            if (target == null) {
                player.sendMessage("§7[§6Coins§7] §cThis Player isn't online.");
                return true;
            }
            int sourceDiff = API.getCoins(player.getUniqueId()) - Integer.parseInt(strings[1]);

            if (sourceDiff < 0) {
                player.sendMessage(
                        "§7[§6Coins§7] §cYou don't have enough Coins to do that!");
                return true;
            }

            if (target == player) {
                player.sendMessage(
                        "§7[§6Coins§7] §cYou can't pay Coins to yourself!");
                return true;
            }

            Bukkit.getPluginManager().callEvent(new PlayerPayCoinsEvent(player, target, Integer.parseInt(strings[1])));

            player.sendMessage(
                    "§7[§6Coins§7] §aSuccessfully payed §e" + target.getName() + "§e " + strings[1]);
            target.sendMessage("§7[§6Coins§7] §aYou've received §e" + strings[1] + " §afrom §e" + player.getName());
        }
        else {
            player.sendMessage(
                    "§7[§6Coins§7] §cUsage: §e/pay <player> <amount>");
        }
        return true;
    }
}
