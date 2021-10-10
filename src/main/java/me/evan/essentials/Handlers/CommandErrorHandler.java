package me.evan.essentials.Handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandErrorHandler {

    public void error_player(Player sender, String message) {
        sender.sendMessage(ChatColor.RED + message);
    }

    public void error_console(String message) {
        System.out.println(ChatColor.RED + message);
    }

    public void warning_player(Player sender, String message) {
        sender.sendMessage(ChatColor.YELLOW + message);
    }

    public void warning_console(String message) {
        System.out.println(ChatColor.YELLOW + message);
    }
}
