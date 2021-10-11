package me.evan.essentials.Commands.General;

import me.evan.essentials.Handlers.CommandAlertHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    CommandAlertHandler alerts = new CommandAlertHandler();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            if(args.length == 0) {
                alerts.error_console("This command is only executable by a player.");
                return false;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if(!(target instanceof Player)) {
                    alerts.alert_console("Please specify a real player.");
                    return false;
                }

                toggle_fly(target);
                alerts.alert_console("You toggled " + target.getDisplayName() + "'s fly mode.");

                return true;
            }
        }

        Player p = (Player) sender;
        if(!(p.hasPermission("essentials.fly") || p.hasPermission("essentials.*"))) {
            alerts.no_permissions(p);
            return false;
        }

        if(args.length == 0) {
            toggle_fly(p);
        } else {
            if(!(p.hasPermission("essentials.fly.others") || p.hasPermission("essentials.*"))) {
                alerts.no_permissions(p);
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(!(target instanceof Player)) {
                alerts.alert_player(p, "Please specify a real player.");
                return false;
            }

            toggle_fly(target);
            alerts.alert_player(p, "You toggled" + target + "'s fly mode.");
        }
        return true;
    }

    void toggle_fly(Player commandTarget) {
        if(commandTarget.isFlying())
        {
            commandTarget.setAllowFlight(false);
            commandTarget.setFlying(false);
            alerts.alert_player(commandTarget, "You are no longer in fly mode.");
        } else {
            commandTarget.setAllowFlight(true);
            alerts.alert_player(commandTarget, "You are now in fly mode.");
        }
    }
}
