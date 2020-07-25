package me.size.commands;

import me.size.util.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            if (strings.length == 1) {


                Player target = Bukkit.getPlayer(strings[0]);
                if (target == null) {
                   // Bukkit.getConsoleSender().sendMessage("§7[§6Coins§7] §aThe Player: §e" + strings[0] + " §a has:§e " + API.getCoinsOffline(strings[0])
                     //       + "§a Coins");
                    return true;
                }

                Bukkit.getConsoleSender().sendMessage(
                        "§7[§6Coins§7] §aThe Player: §e" + target.getName() + " §a has:§e " + API.getCoins(target.getUniqueId())
                                + "§a Coins");
            }
            return true;
        }
        Player player = (Player) commandSender;

        if (strings.length == 0) {
            player.sendMessage(
                    "§7[§6Coins§7] §aYou have:§e " + API.getCoins(player.getUniqueId())
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
                    "§7[§6Coins§7] §aThe Player: §e" + target.getName() + " §a has:§e " + API.getCoins(target.getUniqueId())
                            + "§a Coins");
        }
        return true;
    }
}
