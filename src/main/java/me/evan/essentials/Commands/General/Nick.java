package me.evan.essentials.Commands.General;

import me.evan.essentials.Handlers.CommandAlertHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {

    CommandAlertHandler alerts = new CommandAlertHandler();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            if(args.length <= 1) {
                alerts.error_console("This command is only usable by a player.");
                return false;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if(!(target instanceof Player)) {
                    alerts.alert_console("Please specify a real player.");
                    return false;
                }
                String nickname = ChatColor.translateAlternateColorCodes('&', args[1]);

                changeTarget(target, nickname, args);
                Bukkit.getLogger().info(ChatColor.YELLOW + "You changed " + target.getName() + "'s username to " + ChatColor.RESET + nickname + ChatColor.YELLOW + ".");

                return true;
            }
        }

        Player p = (Player) sender;
        if(!(p.hasPermission("essentials.nick") || p.hasPermission("essentials.*"))) {
            alerts.no_permissions(p);
            return false;
        }

        switch(args.length) {
            case 0:
                alerts.alert_player(p, "Please specify arguments for this command!");
                break;
            case 1:
                changeSingle(p, args);
                break;
            default:
                if(!(p.hasPermission("essentials.nick.others") || p.hasPermission("essentials.*"))) {
                    alerts.no_permissions(p);
                    return false;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if(!(target instanceof Player)) {
                    alerts.alert_player(p, "Please specify a real player as a target!");
                    return false;
                }
                String nickname = ChatColor.translateAlternateColorCodes('&', args[1]);

                changeTarget(target, nickname, args);
                p.sendMessage(ChatColor.YELLOW + "You changed " + target.getName() + "'s username to " + ChatColor.RESET + nickname + ChatColor.YELLOW + ".");
        }

        return true;
    }

    void changeSingle(Player player, String[] arguments) {
        String nickname = ChatColor.translateAlternateColorCodes('&', arguments[0]);

        player.setDisplayName(nickname);
        player.sendMessage(ChatColor.YELLOW + "Your nickname was changed to " + ChatColor.RESET + nickname + ChatColor.YELLOW + ".");
    }

    void changeTarget(Player commandTarget, String nick, String[] arguments) {
        commandTarget.setDisplayName(nick);
        commandTarget.sendMessage(ChatColor.YELLOW + "Your nickname was changed to " + ChatColor.RESET +  nick + ChatColor.YELLOW + ".");
    }
}
