package me.evan.essentials.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandAlertHandler {

    public void error_player(Player error_target, String message) {
        error_target.sendMessage(ChatColor.RED + message);
    }

    public void error_console(String message) {
        Bukkit.getLogger().info(ChatColor.RED + message);
    }

    public void alert_player(Player alert_target, String message) {
        alert_target.sendMessage(ChatColor.YELLOW + message);
    }

    public void alert_console(String message) {
        Bukkit.getLogger().info(ChatColor.YELLOW + message);
    }
}
