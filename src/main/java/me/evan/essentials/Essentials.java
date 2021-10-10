package me.evan.essentials;

import me.evan.essentials.Events.JoinLeave;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Essentials extends JavaPlugin {

    @Override
    public void onEnable() {
        // Startup logic
        getServer().getPluginManager().registerEvents(new JoinLeave(), this);

        getLogger().info(ChatColor.YELLOW + "Essentials enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
