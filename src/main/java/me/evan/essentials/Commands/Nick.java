package me.evan.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            System.out.println("This command is only usable by a player.");
            return false;
        }

        Player p = (Player) sender;
        if(p.hasPermission("essentials.nick") || p.hasPermission("essentials.*")) {
            if(args.length == 1) {
                String nickname = ChatColor.translateAlternateColorCodes('&', args[0]);

                p.setDisplayName(nickname);
                p.sendMessage(ChatColor.YELLOW + "Your nickname was changed to " + ChatColor.RESET + nickname + ChatColor.YELLOW + ".");
            } else if(args.length >= 2) {
                if(p.hasPermission("essentials.nick.others") || p.hasPermission("essentials.*")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(!(target instanceof Player)) {
                        p.sendMessage(ChatColor.RED + "Please specify a real player.");
                        return false;
                    }

                    String nickname = ChatColor.translateAlternateColorCodes('&', args[1]);

                    target.setDisplayName(nickname);
                    p.sendMessage(ChatColor.YELLOW + "You changed " + target.getName() + "'s username to " + ChatColor.RESET + nickname + ChatColor.YELLOW + ".");
                    target.sendMessage(ChatColor.YELLOW + "Your nickname was changed to " + ChatColor.RESET +  nickname + ChatColor.YELLOW + ".");
                } else {
                    p.sendMessage(ChatColor.RED + "You do not have permission to target other players with this command.");
                }
            } else {
                p.sendMessage(ChatColor.RED + "Please specify arguments for this command!");
            }
        } else {
            p.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
        }

        return true;
    }
}
