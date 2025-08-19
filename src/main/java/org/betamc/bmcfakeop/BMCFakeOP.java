package org.betamc.bmcfakeop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class BMCFakeOP extends JavaPlugin {
    public void onEnable() {
        getServer().getLogger().info("[BMCFakeOP] Plugin loaded.");
    }

    public void onDisable() { getServer().getLogger().info("[BMCFakeOP] Plugin unloaded."); }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof ConsoleCommandSender)) {
            if (!(sender.hasPermission("bmcfakeop.commands") || sender.isOp())) {
                sender.sendMessage(ChatColor.RED + "Error: No permission for this command");
                return true;
            }
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Error: Not enough arguments");
            return false;
        }

        String senderUsername = ((Player)sender).getName();
        Player recievingPlayer = Bukkit.getPlayer(args[0]);

        if (recievingPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Error: No online player with specified username");
            return true;
        }

        String recievingPlayerFullName = recievingPlayer.getName();

        if (label.equalsIgnoreCase("fakeop")) {
            recievingPlayer.sendMessage(ChatColor.YELLOW + "You are now op!");
            sender.sendMessage(ChatColor.GREEN + "Fake OP message sent to " + recievingPlayerFullName);
            getServer().getLogger().info("[BMCFakeOP] " + senderUsername + " sent fake OP message to " + recievingPlayerFullName);
        }
        else if (label.equalsIgnoreCase("fakedeop")) {
            recievingPlayer.sendMessage(ChatColor.YELLOW + "You are no longer op!");
            sender.sendMessage(ChatColor.GREEN + "Fake De-OP message sent to " + recievingPlayerFullName);
            getServer().getLogger().info("[BMCFakeOP] " + senderUsername + " sent fake De-OP message to " + recievingPlayerFullName);
        }

        return true;
    }
}
