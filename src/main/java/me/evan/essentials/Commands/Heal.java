package me.evan.essentials.Commands;

import me.evan.essentials.Handlers.CommandAlertHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    CommandAlertHandler alerts = new CommandAlertHandler();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            if(args.length == 0) {
                alerts.error_console("This command cannot be executed by a player.");
                return false;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if(!(target instanceof Player)) {
                    alerts.alert_console("Please specify a real player.");
                    return false;
                }

                heal_player(target);
                alerts.alert_console("You healed" + target.getDisplayName() + ".");

                return true;
            }
        }

        Player p = (Player) sender;
        if(!(p.hasPermission("essentials.heal") || p.hasPermission("essentials.*"))) {
            alerts.no_permissions(p);
            return false;
        }

        if(args.length == 0) {
            heal_player(p);
        } else {
            if(!(p.hasPermission("essentials.heal.others") || p.hasPermission("essentials.*"))) {
                alerts.no_permissions(p);
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(!(target instanceof Player)) {
                alerts.alert_player(p, "Please specify a real player as a target.");
                return false;
            }

            heal_player(target);
            alerts.alert_player(p, "You healed " + target.getDisplayName() + ".");
        }

        return true;
    }

    void heal_player(Player commandTarget) {
        commandTarget.setHealth(20);
        alerts.alert_player(commandTarget, "You were healed.");
    }
}
