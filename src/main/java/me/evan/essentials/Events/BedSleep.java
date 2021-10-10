package me.evan.essentials.Events;

import me.evan.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class BedSleep implements Listener {

    Plugin plugin = Essentials.getPlugin(Essentials.class);

    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        Player p = e.getPlayer();

        if(plugin.getConfig().getBoolean("enableBedSkip") == true) {
            long time = getServer().getWorld("world").getTime();
            if(time < 12300 || time > 2385) {

                getServer().getWorld("world").setTime(1000);

                e.setCancelled(true);
                Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.YELLOW + " changed time to day by going to sleep.");
            } else {
                p.sendMessage(ChatColor.YELLOW + "It is not nighttime!");
            }
        }
    }
}
