package me.evan.essentials.Commands.General;

import me.evan.essentials.Handlers.CommandAlertHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {
    CommandAlertHandler alerts = new CommandAlertHandler();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            if(args.length == 0) {
                alerts.error_console("This command can only be executed by a player.");
                return false;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if(!(target instanceof Player)) {
                    alerts.alert_console("Please specify a real player as a target.");
                    return false;
                }

                god_toggle(target);
                alerts.alert_console("You toggled " + target.getDisplayName() + "'s god mode.");
                return true;
            }
        }

        Player p = (Player) sender;
        if(!(p.hasPermission("essentials.god") || p.hasPermission("essentials.*"))) {
           alerts.no_permissions(p);
           return false;
        }

        if(args.length == 0) {
            god_toggle(p);
        } else {
            if(!(p.hasPermission("essentials.god.others") || p.hasPermission("essentials.*"))) {
                alerts.no_permissions(p);
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(!(target instanceof Player)) {
                alerts.alert_player(p, "Please specify a real player as a target.");
                return false;
            }

            god_toggle(target);
            alerts.alert_player(p, "You toggled " + target.getDisplayName() + "'s god mode.");
        }
        return true;
    }

    void god_toggle(Player commandTarget) {
        if(commandTarget.isInvulnerable()) {
            commandTarget.setInvulnerable(false);
            alerts.alert_player(commandTarget, "You are no longer in god mode!");
        } else {
            commandTarget.setInvulnerable(true);
            alerts.alert_player(commandTarget, "You are now in god mode!");
        }
    }
}
