package me.evan.essentials.Events;

import me.evan.essentials.Essentials;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class JoinLeave implements Listener {

    Plugin plugin = Essentials.getPlugin(Essentials.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("joinMessage")) + " " + ChatColor.GRAY + p.getDisplayName());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("quitMessage")) + " " + ChatColor.GRAY + p.getDisplayName());
    }
}
