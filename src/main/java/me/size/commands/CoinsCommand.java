package me.size.commands;

import me.size.util.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;

        if (strings.length == 0) {
            player.sendMessage(
                    "§7[§6Coins§7] §aYou have:§e " + CoinsAPI.getCoins(player.getUniqueId())
                            + "§a Coins");
        }
        else if (strings.length == 1) {
            if (!player.hasPermission("coins.get.other")) {
                return true;
            }

            Player target = Bukkit.getPlayer(strings[0]);
            if (target == null) {
                player.sendMessage("§7[§6Coins§7] §cThis Player isn't online.");
                return true;
            }

            player.sendMessage(
                    "§7[§6Coins§7] §aThe Player: §e" + target.getName() + " §a has:§e " + CoinsAPI.getCoins(target.getUniqueId())
                            + "§a Coins");
        }
        return true;
    }
}
