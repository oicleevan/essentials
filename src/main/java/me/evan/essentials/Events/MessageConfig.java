package me.evan.essentials.Events;

import me.evan.essentials.Essentials;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class MessageConfig implements Listener {
    Plugin plugin = Essentials.getPlugin(Essentials.class);

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String messageFormat = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messageFormat").replace("%displayname%", p.getDisplayName()));

        e.setFormat(messageFormat + " " + e.getMessage());
    }
}
